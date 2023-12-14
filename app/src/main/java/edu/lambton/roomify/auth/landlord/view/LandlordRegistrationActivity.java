package edu.lambton.roomify.auth.landlord.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import edu.lambton.roomify.auth.common.firebase.NetworkApi;
import edu.lambton.roomify.auth.common.firebase.NetworkApiImpl;
import edu.lambton.roomify.auth.landlord.controller.LandlordController;
import edu.lambton.roomify.common.UserType;
import edu.lambton.roomify.databinding.ActivityLandlordRegistrationBinding;

public class LandlordRegistrationActivity extends AppCompatActivity {

    private ActivityLandlordRegistrationBinding binding;

    private int userTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandlordRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userTypeId = getIntent().getIntExtra("userType", 0);
        binding.createAccountButton.setOnClickListener(this::createLandlordAccount);
        binding.loginScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LandlordLoginActivity
                Intent intent = new Intent(LandlordRegistrationActivity.this, LandlordLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createLandlordAccount(View view) {
        signup();
    }

    private void signup() {
        String fullName = Objects.requireNonNull(binding.fullNameTxt.getText()).toString();
        String email = Objects.requireNonNull(binding.emailTxt.getText()).toString();
        String password = Objects.requireNonNull(binding.passwordTxt.getText()).toString();
        String confirmPassword = Objects.requireNonNull(binding.repeatPasswordTxt.getText()).toString();
        String loginButton = Objects.requireNonNull(binding.loginScreenButton.getText()).toString();

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
            boolean accountCreated;
            if (userTypeId > 0) {
                accountCreated = landlordController.createAccount(email, password, fullName, "", "", "", this, userTypeId);
            } else {
                accountCreated = landlordController.createAccount(email, password, fullName, "", "", "", this, UserType.LANDLORD.getValue());
            }

            Toast.makeText(this, "Account created" + accountCreated, Toast.LENGTH_LONG).show();

            if (accountCreated) {
                binding.fullNameTxt.setText("");
                binding.emailTxt.setText("");
                binding.passwordTxt.setText("");
                binding.repeatPasswordTxt.setText("");
            } else {
                Toast.makeText(this, "An error had occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }
}