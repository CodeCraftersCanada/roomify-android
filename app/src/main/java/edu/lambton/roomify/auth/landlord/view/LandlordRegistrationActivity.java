package edu.lambton.roomify.auth.landlord.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import edu.lambton.roomify.auth.common.firebase.NetworkApi;
import edu.lambton.roomify.auth.common.firebase.NetworkApiImpl;
import edu.lambton.roomify.auth.landlord.controller.LandlordController;
import edu.lambton.roomify.databinding.ActivityLandlordRegistrationBinding;

public class LandlordRegistrationActivity extends AppCompatActivity {

    private ActivityLandlordRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandlordRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAccountButton.setOnClickListener(this::createLandlordAccount);
    }

    private void createLandlordAccount(View view) {
        signup();
    }

    private void signup() {
        String fullName = Objects.requireNonNull(binding.fullNameTxt.getText()).toString();
        String email = Objects.requireNonNull(binding.emailTxt.getText()).toString();
        String password = Objects.requireNonNull(binding.passwordTxt.getText()).toString();
        String confirmPassword = Objects.requireNonNull(binding.repeatPasswordTxt.getText()).toString();

        if (email.equals("") || password.equals("") || confirmPassword.equals("")) {
            binding.fullNameTxt.setError("Name is required");
            binding.emailTxt.setError("Email is required");
            binding.passwordTxt.setError("Password is required");
            binding.repeatPasswordTxt.setError("Confirm Password is required");
            return;
        }

        if (password.equals(confirmPassword)) {

            NetworkApi networkApi = new NetworkApiImpl();
            LandlordController landlordController = new LandlordController(networkApi);

            landlordController.performLandlordOperations();
            boolean accountCreated = landlordController.createAccount(email, password, fullName, "", "", "", this);

            Toast.makeText(this, "Account created" + accountCreated, Toast.LENGTH_LONG).show();

            if (accountCreated) {
                binding.fullNameTxt.setText("");
                binding.emailTxt.setText("");
                binding.passwordTxt.setText("");
                binding.repeatPasswordTxt.setText("");
            }
        }
    }
}