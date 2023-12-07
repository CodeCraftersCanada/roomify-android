package edu.lambton.roomify.landlord.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import edu.lambton.roomify.databinding.FragmentProfileLandlordBinding;

public class ProfileLandlordFragment extends Fragment {

    FragmentProfileLandlordBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileLandlordBinding.inflate(getLayoutInflater(), container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        binding.fullNameLandlord.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());
        binding.emailLandlord.setText(Objects.requireNonNull(mAuth.getCurrentUser().getEmail()));
        return binding.getRoot();
    }

}