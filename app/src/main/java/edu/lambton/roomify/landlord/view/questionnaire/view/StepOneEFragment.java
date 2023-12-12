package edu.lambton.roomify.landlord.view.questionnaire.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.lambton.roomify.databinding.FragmentStep1EBinding;

public class StepOneEFragment extends Fragment {

    private FragmentStep1EBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentStep1EBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}