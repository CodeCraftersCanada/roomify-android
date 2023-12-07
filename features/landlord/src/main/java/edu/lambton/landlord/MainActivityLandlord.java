package edu.lambton.landlord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.lambton.landlord.controller.LandlordController;
import edu.lambton.landlord.databinding.ActivityMainLandlordBinding;
import edu.lambton.networking.firebase.FirebaseManager;
import edu.lambton.networking.firebase.NetworkApi;
import edu.lambton.networking.firebase.NetworkApiImpl;

public class MainActivityLandlord extends AppCompatActivity {
    private final static String TAG = MainActivityLandlord.class.getSimpleName();

    private ActivityMainLandlordBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainLandlordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAccountButton.setOnClickListener(this::createAccount);
    }

    public void createAccount(View view) {

        Toast.makeText(this, "Create Account", Toast.LENGTH_LONG).show();
        signup();
    }


    private void signup() {
        String fullName = binding.fullNameTxt.getText().toString();
        String email = binding.emailTxt.getText().toString();
        String password = binding.passwordTxt.getText().toString();
        String confirmPassword = binding.repeatPasswordTxt.getText().toString();

        if (email.equals("") || password.equals("") || confirmPassword.equals("")) {
            binding.fullNameTxt.setError("Name is required");
            binding.emailTxt.setError("Email is required");
            binding.passwordTxt.setError("Password is required");
            binding.repeatPasswordTxt.setError("Confirm Password is required");
            return;
        }

        if (password.equals(confirmPassword)) {

            NetworkApi networkApi = new NetworkApiImpl();
            LandlordController landlordController = new LandlordController(networkApi);

            landlordController.performLandlordOperations();
            boolean accountCreated = landlordController.createAccount(email, password, fullName, MainActivityLandlord.this);

            Toast.makeText(this, "Account created" + accountCreated, Toast.LENGTH_LONG).show();

            if(accountCreated) {
                binding.fullNameTxt.setText("");
                binding.emailTxt.setText("");
                binding.passwordTxt.setText("");
                binding.repeatPasswordTxt.setText("");
            }

            /*mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Sign in successful
                    Toast.makeText(getApplicationContext(), "User/Pass has been created successful", Toast.LENGTH_SHORT).show();

                    FirebaseUser currentUser = task.getResult().getUser();
                    if (currentUser != null) {

                        User user = new User();
                        user.setAccountId(currentUser.getUid());
                        user.setEmail(currentUser.getEmail());

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", user);

                        Intent loginIntent = new Intent(getApplicationContext(), LoginFragment.class);
                        loginIntent.putExtras(bundle);

                        startActivity(loginIntent);
                    }
                } else {
                    // sign in failed
                    Toast.makeText(getApplicationContext(), "User/Password incorrect", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

    private void signInWithEmailPassword() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}