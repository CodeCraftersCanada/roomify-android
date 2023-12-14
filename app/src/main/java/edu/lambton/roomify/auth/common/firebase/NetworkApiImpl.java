package edu.lambton.roomify.auth.common.firebase;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.lambton.roomify.auth.landlord.dto.UserRequest;
import edu.lambton.roomify.auth.landlord.dto.UserResponse;
import edu.lambton.roomify.landlord.services.ApiService;
import edu.lambton.roomify.landlord.services.RoomifyApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public boolean createAccountOnFirebase(String email, String password, String username, String photoNumber, String college, String address, @NonNull Activity activity, int userType) {
        mAuth = FirebaseAuth.getInstance();
        AtomicBoolean isUserCreated = new AtomicBoolean(false);

        Log.i(TAG, "Creating UserId with email and password");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {

            if (task.isSuccessful()) {
                Log.i(TAG, "UserId created successfully");
                // Sign up success, update UI with the signed-in user's information
                FirebaseUser user = mAuth.getCurrentUser();
                assert user != null;

                // TODO: Save the user into the database
                UserRequest userRequest = new UserRequest(user.getUid(), user.getEmail(), password, userType, username, photoNumber, college, address, null, 0, 0);
                ApiService apiService = RoomifyApiClient.getApiService();

                apiService.createUser(userRequest).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            System.out.println("UserId saved successfully into the Database.");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });


                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();


                user.updateProfile(profileUpdates).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        Log.i(TAG, "Profile updated successfully");
                        Toast.makeText(activity.getApplicationContext(), "UserId profile updated.", Toast.LENGTH_SHORT).show();
                    }
                });

                isUserCreated.set(true);
            } else {
                // If sign up fails, display a message to the user.
                Toast.makeText(activity.getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
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
        }).addOnFailureListener(error -> {
            System.err.println(error.getMessage());
        });
    }

    public interface OnLoginCompleteListener {
        void onLoginComplete(boolean success);
    }
}
