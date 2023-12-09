package edu.lambton.roomify.auth.landlord.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import edu.lambton.roomify.IntroductionListPropertyActivity;
import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.FragmentListLandlordPropertyBinding;
import edu.lambton.roomify.landlord.view.PropertyListingQuestionaryActivity;

public class ListLandlordPropertyFragment extends Fragment {

    private FragmentListLandlordPropertyBinding binding;
    private ExtendedFloatingActionButton listNewPropertyButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListLandlordPropertyBinding.inflate(inflater, container, false);
        listNewPropertyButton = binding.listNewPropertyButton;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listNewPropertyButton.setOnClickListener(this::startQuestionnaire);
    }

    private void startQuestionnaire(View view) {
        Intent startQuestionnaire = new Intent(requireContext(), IntroductionListPropertyActivity.class);
        startActivity(startQuestionnaire);
    }
}