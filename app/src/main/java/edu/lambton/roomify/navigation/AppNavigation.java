package edu.lambton.roomify.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void navigateToStudentRegistration(Context context) {
        Intent intent = new Intent(context, null);
        context.startActivity(intent);
    }

    public void navigateToLandlordRegistration(Context context) {
        Intent intent = new Intent(context, null);
        context.startActivity(intent);
    }
}
