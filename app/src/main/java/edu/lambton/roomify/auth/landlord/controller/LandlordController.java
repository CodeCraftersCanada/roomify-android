package edu.lambton.roomify.auth.landlord.controller;

import android.app.Activity;

import androidx.annotation.NonNull;

import edu.lambton.roomify.auth.common.firebase.NetworkApi;
import edu.lambton.roomify.auth.common.firebase.NetworkApiImpl;


public class LandlordController {
    private final NetworkApi networkApi;

    public LandlordController(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void performLandlordOperations() {
        networkApi.connectToFirebase();

        networkApi.fetchData();
    }

    public boolean createAccount(String email, String password, String fullName, String photoNumber, String college, String address, @NonNull Activity activity) {
        return networkApi.createAccountOnFirebase(email, password, fullName, photoNumber, college, address, activity);
    }

    public void loginLandlordAccount(String email, String password, NetworkApiImpl.OnLoginCompleteListener onLoginCompleteListener) {
        networkApi.signInWithEmailPassword(email, password, onLoginCompleteListener);
    }
}
