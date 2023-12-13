package edu.lambton.roomify.navigation.landlord;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandlordDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

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
            } else if (itemId == R.id.nav_profile_landlord) {
                destinationId = R.id.profileLandlordFragment;
            } else {
                return false;
            }

            dashboardNavController.navigate(destinationId);
            return true;
        });

        // Set up ActionBar with NavController
        NavigationUI.setupActionBarWithNavController(this, dashboardNavController);

        // Set up Bottom Navigation using the Landlord navigation graph
        /*int destinationId = getIntent().getIntExtra("destinationId", R.id.);


        // Set up Bottom Navigation using the Landlord navigation graph
        NavController dashboardNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_landlord);
        dashboardNavController.navigate(destinationId); // Navigate to the specified destination
        NavigationUI.setupActionBarWithNavController(this, dashboardNavController);*/

        //BottomNavigationView bottomNavigationView;
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_landlord);
        return NavigationUI.navigateUp(navController, binding.);
    }*/
}
