package edu.lambton.roomify.chat.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import edu.lambton.roomify.chat.dto.Message;

public class ChatMessageRepository {
    private final FirebaseFirestore firestore;
    private final MutableLiveData<List<Message>> messagesLiveData;
    private final FirebaseAuth auth;
    private final StorageReference storageReference;
    ;

    public ChatMessageRepository() {
        this.firestore = FirebaseFirestore.getInstance();
        this.messagesLiveData = new MutableLiveData<>();
        this.auth = FirebaseAuth.getInstance();
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
                                result.setValue(true);
                            } else {
                                result.setValue(false);
                            }
                        });
            });
        });

        return result;
    }

    @NonNull
    private CollectionReference getMessagesCollection() {
        // Return the reference to the "messages" collection
        return firestore.collection("messages");
    }

    public LiveData<List<Message>> observeMessages() {
        // Observe the "messages" collection in real-time and update the LiveData

        getMessagesCollection()
                .orderBy("milliseconds", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    List<DocumentSnapshot> documents = value.getDocuments();
                    List<Message> messages = new ArrayList<>();
                    documents.forEach(documentSnapshot -> {
                        Message message = documentSnapshot.toObject(Message.class);

                        messages.add(message);
                        System.out.println(message.getText());

                        // Get the image path based on the sender UID
                        if (message.getSenderId() != null) {
                            String imagePath = getUserImagePath(message.getSenderId());
                            System.out.println("Image Path: " + imagePath);
                            // Now you have the image path and can use it as needed
                        }
                    });
                    messagesLiveData.postValue(messages);
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

}
