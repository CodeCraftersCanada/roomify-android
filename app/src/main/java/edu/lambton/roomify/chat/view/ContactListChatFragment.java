package edu.lambton.roomify.chat.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.lambton.roomify.databinding.FragmentContactListChatBinding;

public class ContactListChatFragment extends Fragment {

    private FragmentContactListChatBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactListChatBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}