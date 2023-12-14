package edu.lambton.roomify.chat.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.chat.dto.Message;
import edu.lambton.roomify.chat.view.adapter.ChatMessageAdapter;
import edu.lambton.roomify.chat.viewmodel.ChatMessageViewModel;
import edu.lambton.roomify.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private RecyclerView chatMessageRecycleView;
    private ChatMessageAdapter chatMessageAdapter;
    private final List<Message> messages = new ArrayList<>();

    private ChatMessageViewModel messageViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentChatBinding.inflate(inflater, container, false);
        binding.sendButton.setOnClickListener(this::composeMessage);
        chatMessageRecycleView = binding.recyclerView;

        chatMessageRecycleView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        chatMessageAdapter = new ChatMessageAdapter(messages, "currentUserId");
        chatMessageRecycleView.setAdapter(chatMessageAdapter);

        // Observe the LiveData for messages
        messageViewModel.getMessagesLiveData().observe(getViewLifecycleOwner(), messages -> {
            // Update the UI with the new list of messages
            chatMessageAdapter.setMessages(messages);
            chatMessageAdapter.notifyDataSetChanged();
            scrollToBottom();
        });

        return binding.getRoot();
    }

    private void composeMessage(View view) {

        String text = binding.textMessage.getText().toString();

        // Send the message to Firebase using ViewModel
        messageViewModel.sendMessage(text, "currentUserId");

        // Clear the input field
        binding.textMessage.setText("");
    }

    private void scrollToBottom() {

        if (chatMessageAdapter.getItemCount() > 0) {
            chatMessageRecycleView.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);
        }
    }
}