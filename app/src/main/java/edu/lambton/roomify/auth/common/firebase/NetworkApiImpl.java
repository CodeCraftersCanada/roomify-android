package edu.lambton.roomify.auth.common.firebase;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.atomic.AtomicBoolean;

public class NetworkApiImpl implements NetworkApi {
    private final String TAG = NetworkApiImpl.class.getSimpleName();

    private FirebaseAuth mAuth;
    /*    private SignInClientImpl oneTapClient;*/


    @Override
    public void fetchData() {
        System.out.println("Connect to Fetch Data");
    }

    @Override
    public void connectToFirebase() {
        System.out.println("Connect to Firebase");
    }

    @Override
    public boolean createAccountOnFirebase(String email, String password, String username, @NonNull Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        AtomicBoolean isUserCreated = new AtomicBoolean(false);

        Log.i(TAG, "Creating User with email and password");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {

            if (task.isSuccessful()) {
                Log.i(TAG, "User created successfully");
                // Sign up success, update UI with the signed-in user's information
                FirebaseUser user = mAuth.getCurrentUser();
                assert user != null;

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Log.i(TAG, "Profile updated successfully");
                                Toast.makeText(activity.getApplicationContext(), "User profile updated.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                isUserCreated.set(true);
            } else {
                // If sign up fails, display a message to the user.
                Toast.makeText(activity.getApplicationContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                isUserCreated.set(false);
            }
        });
        return isUserCreated.get();
    }

    @Override
    public void signInWithEmailPassword(String email, String password, OnLoginCompleteListener listener) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            // Notify the listener when the operation is complete
            listener.onLoginComplete(task.isSuccessful());
        });
    }

    public interface OnLoginCompleteListener {
        void onLoginComplete(boolean success);
    }
}
