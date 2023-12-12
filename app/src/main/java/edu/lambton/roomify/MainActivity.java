package edu.lambton.roomify;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import edu.lambton.roomify.auth.landlord.view.LandlordLoginActivity;
import edu.lambton.roomify.auth.student.view.StudentLoginActivity;
import edu.lambton.roomify.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> permissionsList;
    private final String[] permissionsStr = {
            android.Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.LOCATION_HARDWARE
    };


    ActivityResultLauncher<String[]> permissionsLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            ArrayList<Boolean> list = new ArrayList<>(result.values());
            permissionsList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (shouldShowRequestPermissionRationale(permissionsStr[i])) {
                    permissionsList.add(permissionsStr[i]);
                } else {
                    hasPermission(getBaseContext(), permissionsStr[i]);
                }
            }
            if (permissionsList.size() > 0) {
                //Some permissions are denied and can be asked again.
                askForPermissions(permissionsList);
            }  //Show alert dialog

        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.landlordButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LandlordLoginActivity.class);
            startActivity(intent);
        });

        binding.studentScreenButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StudentLoginActivity.class);
            startActivity(intent);
        });

        permissionsList = new ArrayList<>();
        permissionsList.addAll(Arrays.asList(permissionsStr));
        askForPermissions(permissionsList);

    }

    private void hasPermission(Context context, String permissionStr) {
        ContextCompat.checkSelfPermission(context, permissionStr);
    }

    private void askForPermissions(@NonNull ArrayList<String> permissionsList) {
        String[] newPermissionStr = new String[permissionsList.size()];
        for (int i = 0; i < newPermissionStr.length; i++) {
            newPermissionStr[i] = permissionsList.get(i);
        }
        if (newPermissionStr.length > 0) {
            permissionsLauncher.launch(newPermissionStr);
        }
    }
}