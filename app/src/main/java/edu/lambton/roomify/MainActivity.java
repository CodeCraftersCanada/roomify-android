package edu.lambton.roomify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.lambton.landlord.MainActivityLandlord;
import edu.lambton.student.MainActivityStudent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button landLordButton = findViewById(R.id.landlordButton);
        landLordButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivityLandlord.class);
            startActivity(intent);
        });

        Button studentButton = findViewById(R.id.studentScreenButton);
        studentButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivityStudent.class);
            startActivity(intent);
        });

    }
}