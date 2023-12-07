package edu.lambton.landlord.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.lambton.landlord.R;
import edu.lambton.landlord.controller.LandlordController;
import edu.lambton.landlord.databinding.ActivityLandlordLoginBinding;
import edu.lambton.networking.firebase.NetworkApi;
import edu.lambton.networking.firebase.NetworkApiImpl;
import edu.lambton.utils.UserType;

public class LandlordLoginActivity extends AppCompatActivity {

    private ActivityLandlordLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandlordLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.landlordLoginButton.setOnClickListener(this::signInLandlord);
    }

    public void signInLandlord(View view) {
        NetworkApi networkApi = new NetworkApiImpl();
        LandlordController landlordController = new LandlordController(networkApi);

        String email = binding.emailTxt.getText().toString();
        String password = binding.passwordTxt.getText().toString();

        if (email.equals("") || password.equals("")) {
            binding.emailTxt.setError("Email is required");
            binding.passwordTxt.setError("Password is required");
            return;
        }

        landlordController.loginLandlordAccount(email, password, success -> {

            if(success) {


                Toast.makeText(this, "MOVE TO ANOTHER SCREEN: " + success, Toast.LENGTH_LONG).show();
            }

        });

    }


}