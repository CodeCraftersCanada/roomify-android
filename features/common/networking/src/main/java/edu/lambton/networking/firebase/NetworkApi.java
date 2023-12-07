package edu.lambton.networking.firebase;

import android.app.Activity;

import androidx.annotation.NonNull;

public interface NetworkApi {
    void fetchData();

    void connectToFirebase();

    boolean createAccountOnFirebase(String email, String password, String username, @NonNull Activity activity);

    void signInWithEmailPassword(String email, String password, NetworkApiImpl.OnLoginCompleteListener onLoginCompleteListener);
}
