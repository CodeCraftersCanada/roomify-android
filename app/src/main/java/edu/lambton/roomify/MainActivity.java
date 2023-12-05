package edu.lambton.roomify;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

import edu.lambton.roomify.auth.landlord.view.LandlordLoginActivity;
import edu.lambton.roomify.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.landlordButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LandlordLoginActivity.class);
            startActivity(intent);
        });


    }
}