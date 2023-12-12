package edu.lambton.roomify.auth.student.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import edu.lambton.roomify.R;
import edu.lambton.roomify.auth.common.firebase.NetworkApi;
import edu.lambton.roomify.auth.common.firebase.NetworkApiImpl;
import edu.lambton.roomify.auth.landlord.controller.LandlordController;
import edu.lambton.roomify.auth.landlord.view.LandlordRegistrationActivity;
import edu.lambton.roomify.databinding.ActivityStudentLoginBinding;
import edu.lambton.roomify.navigation.landlord.LandlordDashboardActivity;
import edu.lambton.roomify.navigation.student.StudentDashboardActivity;

public class StudentLoginActivity extends AppCompatActivity {

    private ActivityStudentLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.landlordLoginButton.setOnClickListener(this::signInStudent);

        binding.landlordSignUpButton.setOnClickListener(this::signUpStudent);
    }

    private void signUpStudent(View view) {
        Intent signupScreen = new Intent(this, LandlordRegistrationActivity.class);
        startActivity(signupScreen);
    }

    private void signInStudent(View view) {
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
                Intent studentDashboardIntent = new Intent(this, StudentDashboardActivity.class);
                startActivity(studentDashboardIntent);
            }

        });
    }
}