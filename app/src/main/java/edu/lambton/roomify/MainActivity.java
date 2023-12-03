package edu.lambton.roomify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.lambton.landlord.MainActivityLandlord;
import edu.lambton.roomify.databinding.ActivityMainBinding;
import edu.lambton.student.MainActivityStudent;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.landlordButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivityLandlord.class);
            startActivity(intent);
        });

        binding.studentScreenButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivityStudent.class);
            startActivity(intent);
        });

    }
}