package edu.lambton.roomify.auth.common.firebase;

import android.app.Activity;

import androidx.annotation.NonNull;

public interface NetworkApi {
    void fetchData();

    void connectToFirebase();

    boolean createAccountOnFirebase(String email, String password, String username, String photoNumber, String college, String address, @NonNull Activity activity);

    void signInWithEmailPassword(String email, String password, NetworkApiImpl.OnLoginCompleteListener onLoginCompleteListener);
}
