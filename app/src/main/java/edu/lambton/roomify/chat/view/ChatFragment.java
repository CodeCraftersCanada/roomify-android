package edu.lambton.roomify.chat.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
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
    private String myContactRecipientUid;

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

        myContactRecipientUid = getIntent().getStringExtra("contactUID");

        chatMessageRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        chatMessageAdapter = new ChatMessageAdapter(messages, mAuth.getUid());
        chatMessageRecycleView.setAdapter(chatMessageAdapter);

        // Set up the custom Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_included);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        messageViewModel = new ViewModelProvider(getViewModelStore(), new ChatMessageViewModelFactory(getApplication())).get(ChatMessageViewModel.class);
        // Observe the LiveData for messages
        messageViewModel.getMessagesLiveData().observe(this, messages -> {
            // Update the UI with the new list of messages
            chatMessageAdapter.setMessages(messages);
            chatMessageAdapter.notifyDataSetChanged();
            scrollToBottom();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void composeMessage(View view) {

        String text = binding.textMessage.getText().toString();

        // Send the message to Firebase using ViewModel
        // Pass the recipient ID

        if (recipientUid != null && !recipientUid.equals("")) {
            messageViewModel.sendMessage(text, recipientUid);
        } else {
            messageViewModel.sendMessage(text, myContactRecipientUid);
        }


        // Clear the input field
        binding.textMessage.setText("");
    }

    private void scrollToBottom() {

        if (chatMessageAdapter.getItemCount() > 0) {
            chatMessageRecycleView.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);
        }
    }
}