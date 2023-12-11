package edu.lambton.roomify.landlord.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import edu.lambton.roomify.databinding.FragmentProfileLandlordBinding;

public class ProfileLandlordFragment extends Fragment {

    FragmentProfileLandlordBinding binding;

    private EditText editFullNameEditText, collegeEditTextView, phoneEditTextView, addressEditTextView;

    private MaterialButton saveProfileButton, editProfileButton;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton callMeButton = binding.callMeButton;
        editProfileButton = binding.editProfileButton;
        editFullNameEditText = binding.editFullName;
        collegeEditTextView = binding.collegeEditTextView;
        phoneEditTextView = binding.phoneEditTextView;
        addressEditTextView = binding.addressEditTextView;

        saveProfileButton = binding.saveProfileButton;
        saveProfileButton.setOnClickListener(this::saveEditedProfile);

        editProfileButton.setOnClickListener(this::toggleToEditProfile);

        callMeButton.setOnClickListener(this::callNumber);
    }

    private void saveEditedProfile(View view) {
        toggleEditMode();
    }

    private void toggleToEditProfile(View view) {
        toggleEditMode();
    }

    private void callNumber(View view) {

        String phoneNumber = "4372637254";

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireContext(), "No app found to handle the call", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleEditMode() {
        boolean isInEditMode = editFullNameEditText.getVisibility() == View.VISIBLE;

        if (isInEditMode) {
            // Save the edited information and switch back to TextView mode
            saveProfileButton.setVisibility(View.VISIBLE);
            editProfileButton.setVisibility(View.INVISIBLE);

            // Example: Save Full Name
            String editedFullName = editFullNameEditText.getText().toString();
            String editedCollege = collegeEditTextView.getText().toString();
            String editedPhone = phoneEditTextView.getText().toString();
            String editedAddress = addressEditTextView.getText().toString();

            // Update the corresponding TextView
            TextView fullNameTextView = binding.fullNameLandlord;
            fullNameTextView.setText(editedFullName);

            TextView collegeTextView = binding.collegeTextView;
            collegeEditTextView.setText(editedCollege);

            TextView phoneTextView = binding.phoneTextView;
            phoneTextView.setText(editedPhone);

            TextView addressTextView = binding.addressTextView;
            addressTextView.setText(editedAddress);

            // Switch back to TextView mode
            editFullNameEditText.setVisibility(View.INVISIBLE);
            collegeEditTextView.setVisibility(View.INVISIBLE);
            phoneEditTextView.setVisibility(View.INVISIBLE);
            addressEditTextView.setVisibility(View.INVISIBLE);

            fullNameTextView.setVisibility(View.VISIBLE);
            collegeTextView.setVisibility(View.VISIBLE);
            phoneTextView.setVisibility(View.VISIBLE);
            addressTextView.setVisibility(View.VISIBLE);

        } else {
            saveProfileButton.setVisibility(View.INVISIBLE);
            editProfileButton.setVisibility(View.VISIBLE);
            // Switch to EditText mode
            editFullNameEditText.setVisibility(View.VISIBLE);
            collegeEditTextView.setVisibility(View.VISIBLE);
            phoneEditTextView.setVisibility(View.VISIBLE);
            addressEditTextView.setVisibility(View.VISIBLE);

            TextView fullNameTextView = binding.fullNameLandlord;
            fullNameTextView.setVisibility(View.INVISIBLE);

            TextView collegeTextView = binding.collegeTextView;
            collegeTextView.setVisibility(View.INVISIBLE);

            TextView phoneTextView = binding.phoneTextView;
            phoneTextView.setVisibility(View.INVISIBLE);

            TextView addressTextView = binding.addressTextView;
            addressTextView.setVisibility(View.INVISIBLE);

            // Set the current information to the EditText
            editFullNameEditText.setText(fullNameTextView.getText().toString());
            collegeEditTextView.setText(collegeTextView.getText().toString());
            phoneEditTextView.setText(phoneTextView.getText().toString());
            addressEditTextView.setText(addressTextView.getText().toString());

        }
    }
}