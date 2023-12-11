package edu.lambton.roomify.landlord.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.ActivityPropertyInfoBinding;
import edu.lambton.roomify.landlord.view.adapter.ImageSliderAdapter;

public class PropertyInfoActivity extends AppCompatActivity {

    private ActivityPropertyInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPropertyInfoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*// Sample data
        int totalImages = 5; // Replace with the actual number of images
        int currentImage = 1; // Replace with the actual current image number

        // Set up the ViewPager
        ViewPager viewPager = binding.viewPagerImages;
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, totalImages);
        viewPager.setAdapter(imageSliderAdapter);

        // Set the current image count
        binding.imageCount.setText(getString(currentImage, totalImages));

        // Set up the RatingBar
        binding.starRating.setRating(4); // Replace with the actual rating

        // Set up the price
        binding.price.setText("100"); // Replace with the actual price

        // Set up other property details as needed

        // Set up the map (replace with your map implementation)
        // You may use a MapView or a static map image, depending on your needs

        // Set up the reserve button click listener
        binding.reserveButton.setOnClickListener(v -> {
            // Handle reserve button click
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Respond to the action bar's Up/Home button
            onBackPressed(); // Go back when the back button is clicked
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}