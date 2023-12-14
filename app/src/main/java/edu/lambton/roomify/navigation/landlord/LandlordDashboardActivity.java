package edu.lambton.roomify.navigation.landlord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.ActivityLandlordDashboardBinding;

public class LandlordDashboardActivity extends AppCompatActivity {

    ActivityLandlordDashboardBinding binding;
    private int userTypeId;
    public static final String PREF_NAME = "MyPrefs";
    public static final String USER_TYPE_KEY = "userType";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandlordDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        userTypeId = getIntent().getIntExtra("userType", 0);

        saveUserTypeIdToPreferences(userTypeId);

        NavController dashboardNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_landlord);

        // Set up Bottom Navigation using the Landlord navigation graph
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationLandlordView);
        NavigationUI.setupWithNavController(bottomNavigationView, dashboardNavController);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int destinationId;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_list_property) {
                destinationId = R.id.listPropertyFragment;
            } else if (itemId == R.id.nav_message_landlord) {
                destinationId = R.id.chatFragment;
            } else if (itemId == R.id.nav_contact_landlord) {
                destinationId = R.id.contactListChatFragment;
            } else if (itemId == R.id.nav_profile_landlord) {
                destinationId = R.id.profileLandlordFragment;
            } else {
                return false;
            }

            // Navigate to the selected destination with or without arguments

            dashboardNavController.navigate(destinationId);

            return true;
        });

        // Set up ActionBar with NavController
        NavigationUI.setupActionBarWithNavController(this, dashboardNavController);

    }

    // Helper method to save userTypeId to SharedPreferences
    private void saveUserTypeIdToPreferences(int userTypeId) {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(USER_TYPE_KEY, userTypeId);
        editor.apply();
    }

    // Helper method to retrieve userTypeId from SharedPreferences


    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_landlord);
        return NavigationUI.navigateUp(navController, binding.);
    }*/
}
