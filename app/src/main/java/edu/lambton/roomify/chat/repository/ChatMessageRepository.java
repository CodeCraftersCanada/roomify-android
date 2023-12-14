package edu.lambton.roomify.chat.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import edu.lambton.roomify.chat.dto.Contact;
import edu.lambton.roomify.chat.dto.Message;
import edu.lambton.roomify.landlord.dto.UserResponse;
import edu.lambton.roomify.landlord.services.ApiService;
import edu.lambton.roomify.landlord.services.RoomifyApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatMessageRepository {
    private final FirebaseFirestore firestore;
    private final MutableLiveData<List<Message>> messagesLiveData;
    private final FirebaseAuth auth;
    private final StorageReference storageReference;

    private ApiService apiService;
    ;

    public ChatMessageRepository() {
        this.firestore = FirebaseFirestore.getInstance();
        this.messagesLiveData = new MutableLiveData<>();
        this.auth = FirebaseAuth.getInstance();
        this.apiService = RoomifyApiClient.getApiService();
        this.storageReference = FirebaseStorage.getInstance().getReference().child("avatar");
        observeMessages();
    }


    public LiveData<Boolean> sendMessage(String text, String recipientUid) {
        final MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Ensure the user is authenticated
        if (auth.getCurrentUser() == null) {
            result.setValue(false);
            return result;
        }

        String senderUID = auth.getCurrentUser().getUid();
        String senderPicturePath = getUserImagePath(senderUID);

        String recipientPicturePath = getUserImagePath(recipientUid);

        Message message = new Message(text, senderUID, recipientUid, System.currentTimeMillis(), senderPicturePath, recipientPicturePath);

        // Add the message to Firestore
        // Get the download URL for sender and recipient avatars
        getUserAvatarDownloadUrl(senderUID, senderAvatarUrl -> {
            message.setImagePathSenderId(senderAvatarUrl);

            getUserAvatarDownloadUrl(recipientUid, recipientAvatarUrl -> {
                message.setImagePathRecipientId(recipientAvatarUrl);

                // Add the message to Firestore
                getMessagesCollection().add(message)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Message sent successfully
                                result.setValue(true);

                                // Update user contacts
                                updateContacts(senderUID, recipientUid);

                                // Create user in "users" collection if not exists
                                createUserIfNotExists(senderUID);
                                createUserIfNotExists(recipientUid);
                            } else {
                                // Failed to send message
                                result.setValue(false);
                            }
                        });
            });
        });

        return result;
    }

    private void createUserIfNotExists(String uid) {
        // Get the reference to the "users" collection
        CollectionReference usersCollection = firestore.collection("users");

        // Check if the user already exists
        usersCollection.document(uid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (!document.exists()) {
                    // Document does not exist, fetch user details from Firebase Authentication

                    apiService.getUserById(uid).enqueue(new Callback<>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful()) {

                                assert response.body() != null;
                                UserResponse.User user = response.body().getUser();
                                String displayName = user.getFullName();
                                String profilePictureUrl = user.getImagePath();

                                usersCollection.document(uid).set(new Contact(displayName, profilePictureUrl, uid));

                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    @NonNull
    private CollectionReference getMessagesCollection() {
        // Return the reference to the "messages" collection
        return firestore.collection("messages");
    }

    public LiveData<List<Message>> observeMessages() {
        // Observe the "messages" collection in real-time and update the LiveData

        String currentUserUid = auth.getCurrentUser().getUid();

        Query senderQuery = getMessagesCollection().whereEqualTo("senderId", currentUserUid);
        Query recipientQuery = getMessagesCollection().whereEqualTo("recipientId", currentUserUid);

        // Execute both queries
        Task<QuerySnapshot> senderQueryTask = senderQuery.get();
        Task<QuerySnapshot> recipientQueryTask = recipientQuery.get();

        // Combine the results when both queries complete
        Tasks.whenAllComplete(senderQueryTask, recipientQueryTask)
                .addOnCompleteListener(allTasks -> {
                    if (allTasks.isSuccessful()) {
                        List<DocumentSnapshot> senderDocuments = ((QuerySnapshot) senderQueryTask.getResult()).getDocuments();
                        List<DocumentSnapshot> recipientDocuments = ((QuerySnapshot) recipientQueryTask.getResult()).getDocuments();

                        List<Message> messages = new ArrayList<>();

                        for (DocumentSnapshot senderDocument : senderDocuments) {
                            Message message = senderDocument.toObject(Message.class);
                            messages.add(message);
                        }

                        for (DocumentSnapshot recipientDocument : recipientDocuments) {
                            Message message = recipientDocument.toObject(Message.class);
                            messages.add(message);
                        }

                        // Sort and update the LiveData
                        messages.sort(Comparator.comparingLong(Message::getMilliseconds));
                        messagesLiveData.postValue(messages);
                    } else {
                        // Handle the error, log it, or notify the user
                        Log.e("Firestore", "Error getting messages: " + allTasks.getException().getMessage());
                    }
                });

        return messagesLiveData;
    }

    @NonNull
    private String getUserImagePath(String uid) {
        // Construct the path to the user's profile image in Firebase Storage
        if (uid == null) {
            return "";
        }
        return storageReference.child(uid).getPath();
    }

    private void getUserAvatarDownloadUrl(String uid, Consumer<String> callback) {
        if (uid == null) {
            return;
        }

        storageReference.child(uid).getDownloadUrl()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String downloadUrl = task.getResult().getPath();
                        callback.accept(downloadUrl);
                    }
                })
                .addOnSuccessListener(uri -> {


                })
                .addOnFailureListener(e -> {
                    String errorMessage = e.getMessage();
                    Log.e("Firebase Storage", "Error getting download URL: " + errorMessage);
                    callback.accept(""); // Return empty string or handle failure as needed
                });
    }

    public LiveData<List<Contact>> fetchMyContacts(String currentUserUID) {
        MutableLiveData<List<Contact>> contactsLiveData = new MutableLiveData<>();

        // Get the reference to the "contacts" collection
        CollectionReference contactsCollection = firestore.collection("contacts");

        // Fetch the user's contacts
        contactsCollection.document(currentUserUID).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<String> contactIds = (List<String>) document.get("contactIds");
                    if (contactIds != null && !contactIds.isEmpty()) {
                        // Fetch user details for each contact ID
                        fetchUserDetails(contactIds, contactsLiveData);
                    } else {
                        // No contacts
                        contactsLiveData.setValue(new ArrayList<>());
                    }
                } else {
                    // No document for the user (no contacts)
                    contactsLiveData.setValue(new ArrayList<>());
                }
            }
        });

        return contactsLiveData;
    }

    private void fetchUserDetails(@NonNull List<String> contactIds, MutableLiveData<List<Contact>> contactsLiveData) {
        // Get the reference to the "users" collection (replace with your actual users collection name)
        CollectionReference usersCollection = firestore.collection("users");

        List<Contact> contacts = new ArrayList<>();

        // Fetch user details for each contact ID
        for (String contactId : contactIds) {
            usersCollection.document(contactId).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot userDocument = task.getResult();
                    if (userDocument.exists()) {
                        // Create a Contact object with user details
                        Contact contact = new Contact(
                                userDocument.getString("displayName"),
                                userDocument.getString("profilePictureUrl"),
                                userDocument.getId()
                        );
                        contacts.add(contact);

                        // Update LiveData when all contacts are fetched
                        if (contacts.size() == contactIds.size()) {
                            contactsLiveData.setValue(contacts);
                        }
                    }
                }
            });
        }
    }

    private void addContact(String uid, String contactUid) {
        // Get the reference to the "contacts" collection
        CollectionReference contactsCollection = firestore.collection("contacts");

        // Check if the user's document exists
        contactsCollection.document(uid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Document exists, update the "contactIds" field
                    List<String> contactIds = (List<String>) document.get("contactIds");
                    if (contactIds == null) {
                        contactIds = new ArrayList<>();
                    }
                    if (!contactIds.contains(contactUid)) {
                        contactIds.add(contactUid);
                        contactsCollection.document(uid).update("contactIds", contactIds);
                    }
                } else {
                    // Document does not exist, create a new document
                    List<String> contactIds = new ArrayList<>();
                    contactIds.add(contactUid);
                    contactsCollection.document(uid).set(new ContactDocument(uid, contactIds));
                }
            } else {
                System.out.println("Something happened");
            }
        }).addOnFailureListener(e -> {

        });
    }

    private void updateContacts(String senderUid, String recipientUid) {
        // Add each user to the other user's contacts
        addContact(senderUid, recipientUid);
        addContact(recipientUid, senderUid);
    }


    // Class to represent the user contacts document in Firestore
    public static class ContactDocument implements Serializable {
        public String userId;
        public List<String> contactIds;

        // Empty constructor for Firestore
        public ContactDocument() {
        }

        public ContactDocument(String userId, List<String> contactIds) {
            this.userId = userId;
            this.contactIds = contactIds;
        }
    }
}
