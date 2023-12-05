package edu.lambton.networking;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class RoomifyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
    }
}
