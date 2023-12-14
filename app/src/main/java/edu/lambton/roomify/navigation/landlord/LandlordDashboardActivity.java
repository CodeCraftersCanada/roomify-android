package edu.lambton.roomify.navigation.landlord;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.ActivityLandlordDashboardBinding;
import edu.lambton.roomify.landlord.view.ListLandlordPropertyFragmentDirections;

public class LandlordDashboardActivity extends AppCompatActivity {

    ActivityLandlordDashboardBinding binding;
    private int userTypeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandlordDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        userTypeId = getIntent().getIntExtra("userType", 0);


        NavController dashboardNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_landlord);

        // Set up Bottom Navigation using the Landlord navigation graph
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationLandlordView);
        NavigationUI.setupWithNavController(bottomNavigationView, dashboardNavController);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int destinationId;
            NavDirections directions = null;


            int itemId = item.getItemId();
            if (itemId == R.id.nav_list_property) {
                destinationId = R.id.listPropertyFragment;
                directions = ListLandlordPropertyFragmentDirections.actionListPropertyFragmentToListProperty(userTypeId);
            } else if (itemId == R.id.nav_message_landlord) {
                destinationId = R.id.chatFragment;
            } else if (itemId == R.id.nav_contact_landlord) {
                destinationId = R.id.contactListChatFragment;
            } else if (itemId == R.id.nav_profile_landlord) {
                destinationId = R.id.profileLandlordFragment;
            } else {
                return false;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("userTypeId", userTypeId);

            if (directions != null) {
                dashboardNavController.navigate(directions);

            }

            return true;
        });

        // Set up ActionBar with NavController
        NavigationUI.setupActionBarWithNavController(this, dashboardNavController);

    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_landlord);
        return NavigationUI.navigateUp(navController, binding.);
    }*/
}
