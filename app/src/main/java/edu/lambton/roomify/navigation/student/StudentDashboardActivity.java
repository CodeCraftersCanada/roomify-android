package edu.lambton.roomify.navigation.student;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.ActivityStudentDashboardBinding;

public class StudentDashboardActivity extends AppCompatActivity {

    private ActivityStudentDashboardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        NavController dashboardNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_student);

        // Set up Bottom Navigation using the Landlord navigation graph
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationStudentView);
        NavigationUI.setupWithNavController(bottomNavigationView, dashboardNavController);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int destinationId;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_explore_home) {
                destinationId = R.id.explorePropertiesFragment;
            } else if (itemId == R.id.nav_message_landlord) {
                destinationId = R.id.profileLandlordFragment;
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

    }
}
