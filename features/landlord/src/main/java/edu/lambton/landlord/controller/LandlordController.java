package edu.lambton.landlord.controller;

import android.app.Activity;

import edu.lambton.networking.firebase.NetworkApi;
import edu.lambton.networking.firebase.NetworkApiImpl;

public class LandlordController {
    private final NetworkApi networkApi;

    public LandlordController(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void performLandlordOperations() {
        networkApi.connectToFirebase();

        networkApi.fetchData();
    }

    public boolean createAccount(String email, String password, String fullName, Activity activity) {
        return networkApi.createAccountOnFirebase(email, password, fullName, activity);
    }

    public void loginLandlordAccount(String email, String password, NetworkApiImpl.OnLoginCompleteListener onLoginCompleteListener) {
        networkApi.signInWithEmailPassword(email, password, onLoginCompleteListener);
    }
}
