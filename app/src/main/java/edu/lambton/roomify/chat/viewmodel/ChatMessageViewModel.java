package edu.lambton.roomify.chat.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.lambton.roomify.chat.dto.Message;
import edu.lambton.roomify.chat.repository.ChatMessageRepository;

public class ChatMessageViewModel extends ViewModel {

    private final ChatMessageRepository messageRepository;
    private LiveData<List<Message>> messagesLiveData;


    public ChatMessageViewModel(Application application) {
        messageRepository = new ChatMessageRepository();
        this.messagesLiveData = messageRepository.observeMessages();
    }

    public LiveData<Boolean> sendMessage(String text, String recipientUid) {
        return messageRepository.sendMessage(text, recipientUid);
    }

    public LiveData<List<Message>> getMessagesLiveData() {
        return messagesLiveData;
    }
}
