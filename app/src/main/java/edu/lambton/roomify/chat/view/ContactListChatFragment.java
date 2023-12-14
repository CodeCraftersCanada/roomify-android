package edu.lambton.roomify.chat.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.chat.dto.Contact;
import edu.lambton.roomify.chat.view.adapter.ContactListChatRecycleViewAdapter;
import edu.lambton.roomify.chat.viewmodel.ChatMessageViewModel;
import edu.lambton.roomify.chat.viewmodel.ChatMessageViewModelFactory;
import edu.lambton.roomify.databinding.FragmentContactListChatBinding;

public class ContactListChatFragment extends Fragment implements ContactListChatRecycleViewAdapter.OnContactClickListener {

    private FirebaseAuth mAuth;
    private FragmentContactListChatBinding binding;
    private RecyclerView contactListRecycleView;
    private ContactListChatRecycleViewAdapter contactListAdapter;

    private final List<Contact> contacts = new ArrayList<>();

    private ChatMessageViewModel chatMessageViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactListChatBinding.inflate(inflater, container, false);
        contactListRecycleView = binding.contactListReviewView;
        contactListRecycleView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        contactListAdapter = new ContactListChatRecycleViewAdapter(contacts, this);
        contactListRecycleView.setAdapter(contactListAdapter);

        chatMessageViewModel = new ViewModelProvider(getViewModelStore(), new ChatMessageViewModelFactory(requireActivity().getApplication())).get(ChatMessageViewModel.class);
        loadAllMyContacts();
        return binding.getRoot();
    }

    public void loadAllMyContacts() {
        String currentUserUID = mAuth.getCurrentUser().getUid();
        chatMessageViewModel.getAllMyContacts(currentUserUID).observe(getViewLifecycleOwner(), contactsList -> {
            contacts.clear();
            this.contacts.addAll(contactsList);
            contactListAdapter.notifyDataSetChanged();
        });
    }


    @Override
    public void onContactClick(@NonNull Contact contact) {
        // TODO: Go to the activity that contain the message:

        Intent intentChatActivity = new Intent(requireContext(), ChatFragment.class);
        intentChatActivity.putExtra("contactUID", contact.getUserId());

        startActivity(intentChatActivity);
    }

}