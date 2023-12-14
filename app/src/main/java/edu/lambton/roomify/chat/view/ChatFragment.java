package edu.lambton.roomify.chat.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.chat.dto.Message;
import edu.lambton.roomify.chat.view.adapter.ChatMessageAdapter;
import edu.lambton.roomify.chat.viewmodel.ChatMessageViewModel;
import edu.lambton.roomify.chat.viewmodel.ChatMessageViewModelFactory;
import edu.lambton.roomify.databinding.FragmentChatBinding;

public class ChatFragment extends AppCompatActivity {

    private FragmentChatBinding binding;
    private RecyclerView chatMessageRecycleView;
    private ChatMessageAdapter chatMessageAdapter;
    private final List<Message> messages = new ArrayList<>();
    private FirebaseAuth mAuth;
    private ChatMessageViewModel messageViewModel;
    private String recipientUid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.sendButton.setOnClickListener(this::composeMessage);
        chatMessageRecycleView = binding.recyclerView;
        // Retrieve recipient UID from arguments

        recipientUid = getIntent().getStringExtra("recipientLandlordUID");

        chatMessageRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        chatMessageAdapter = new ChatMessageAdapter(messages, mAuth.getUid());
        chatMessageRecycleView.setAdapter(chatMessageAdapter);


        messageViewModel = new ViewModelProvider(getViewModelStore(), new ChatMessageViewModelFactory(getApplication())).get(ChatMessageViewModel.class);
        // Observe the LiveData for messages
        messageViewModel.getMessagesLiveData().observe(this, messages -> {
            // Update the UI with the new list of messages
            chatMessageAdapter.setMessages(messages);
            chatMessageAdapter.notifyDataSetChanged();
            scrollToBottom();
        });
    }


    private void composeMessage(View view) {

        String text = binding.textMessage.getText().toString();

        // Send the message to Firebase using ViewModel
        // Pass the recipient ID
        messageViewModel.sendMessage(text, recipientUid);

        // Clear the input field
        binding.textMessage.setText("");
    }

    private void scrollToBottom() {

        if (chatMessageAdapter.getItemCount() > 0) {
            chatMessageRecycleView.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);
        }
    }
}