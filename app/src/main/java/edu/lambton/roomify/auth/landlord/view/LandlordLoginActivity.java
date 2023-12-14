package edu.lambton.roomify.auth.landlord.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import edu.lambton.roomify.auth.common.firebase.NetworkApi;
import edu.lambton.roomify.auth.common.firebase.NetworkApiImpl;
import edu.lambton.roomify.auth.landlord.controller.LandlordController;
import edu.lambton.roomify.common.UserType;
import edu.lambton.roomify.databinding.ActivityLandlordLoginBinding;
import edu.lambton.roomify.navigation.landlord.LandlordDashboardActivity;

public class LandlordLoginActivity extends AppCompatActivity {

    private ActivityLandlordLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandlordLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.landlordLoginButton.setOnClickListener(this::signInLandlord);

        binding.landlordSignUpButton.setOnClickListener(this::signUpLandlord);
    }

    private void signUpLandlord(View view) {
        Intent signupScreen = new Intent(this, LandlordRegistrationActivity.class);
        startActivity(signupScreen);
    }

    public void signInLandlord(View view) {
        NetworkApi networkApi = new NetworkApiImpl();
        LandlordController landlordController = new LandlordController(networkApi);

        String email = Objects.requireNonNull(binding.emailTxt.getText()).toString();
        String password = Objects.requireNonNull(binding.passwordTxt.getText()).toString();

        if (email.equals("") || password.equals("")) {
            binding.emailTxt.setError("Email is required");
            binding.passwordTxt.setError("Password is required");
            return;
        }

        landlordController.loginLandlordAccount(email, password, success -> {

            if (success) {

                Intent landlordDashboardIntent = new Intent(this, LandlordDashboardActivity.class);
                landlordDashboardIntent.putExtra("userType", UserType.LANDLORD.getValue());

                startActivity(landlordDashboardIntent);
            }

        });

    }

}