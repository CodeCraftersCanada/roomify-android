package edu.lambton.roomify.chat.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.chat.dto.Message;

public class ChatMessageRepository {
    private final FirebaseFirestore firestore;
    private final MutableLiveData<List<Message>> messagesLiveData;
    private final FirebaseAuth auth;


    public ChatMessageRepository() {
        this.firestore = FirebaseFirestore.getInstance();
        this.messagesLiveData = new MutableLiveData<>();
        this.auth = FirebaseAuth.getInstance();
        observeMessages();
    }


    public LiveData<Boolean> sendMessage(String text, String recipientUid) {
        final MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Ensure the user is authenticated
        if (auth.getCurrentUser() == null) {
            result.setValue(false);
            return result;
        }

        Message message = new Message(text, auth.getCurrentUser().getUid(), recipientUid, System.currentTimeMillis());

        // Add the message to Firestore
        getMessagesCollection().add(message)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        result.setValue(true);
                    } else {
                        result.setValue(false);
                    }
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
                    });
                    messagesLiveData.postValue(messages);
                });

        return messagesLiveData;
    }

}
