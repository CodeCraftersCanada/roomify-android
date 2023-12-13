package edu.lambton.roomify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.lambton.roomify.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentChatBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}