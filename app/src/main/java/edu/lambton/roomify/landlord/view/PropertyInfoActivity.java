package edu.lambton.roomify.landlord.view;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.lambton.roomify.R;
import edu.lambton.roomify.common.UserType;
import edu.lambton.roomify.databinding.ActivityPropertyInfoBinding;
import edu.lambton.roomify.landlord.dto.PropertyResponseInfo;
import edu.lambton.roomify.landlord.view.adapter.ImageSliderAdapter;
import edu.lambton.roomify.landlord.viewmodel.ProperetyLandlordViewModelFactory;
import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class PropertyInfoActivity extends AppCompatActivity {

    private ActivityPropertyInfoBinding binding;
    private PropertyLandlordViewModel propertyLandlordViewModel;
    private final List<PropertyResponseInfo.PhotoDTO> propertyImages = new ArrayList<>();
    private ImageSliderAdapter imageSliderAdapter;
    private TextView imageCountTextView;
    private ViewPager viewPager;

    private ImageView staticMapImageView;


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

        viewPager = binding.viewPagerImages;
        imageCountTextView = binding.imageCount;
        staticMapImageView = binding.staticMapImageView;

        // Get property latitude and longitude (replace with your actual logic)


        propertyLandlordViewModel = new ViewModelProvider(getViewModelStore(),
                new ProperetyLandlordViewModelFactory(getApplication())).get(PropertyLandlordViewModel.class);

        String propertyId = PropertyInfoActivityArgs.fromBundle(getIntent().getExtras()).getPropertyId();

        imageSliderAdapter = new ImageSliderAdapter(this, propertyImages);
        viewPager.setAdapter(imageSliderAdapter);

        propertyLandlordViewModel.getPropertyInfo(propertyId).observe(this, propertyResponse -> {
            runOnUiThread(() -> {
                propertyImages.clear();

                List<PropertyResponseInfo.PhotoDTO> newPhotos = propertyResponse.getProperty().getPhotos();
                propertyImages.addAll(newPhotos);

                imageSliderAdapter.notifyDataSetChanged();
                updateImageCountText(viewPager.getCurrentItem() + 1, propertyImages.size());
            });

            PropertyResponseInfo.PropertyDTO property = propertyResponse.getProperty();
            binding.price.setText(String.valueOf(property.getPrice().getNumberDecimal()));
            binding.description.setText(property.getDescription());

            double propertyLatitude = property.getLatitude();
            double propertyLongitude = property.getLongitude();

            String apiKey = getGoogleMapsApiKey();
            // Load static map using Google Maps Static API
            String staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap" +
                    "?center=" + propertyLatitude + "," + propertyLongitude +
                    "&zoom=15" +
                    "&size=400x200" +  // Adjust size as needed
                    "&markers=color:red%7Clabel:P%7C" + propertyLatitude + "," + propertyLongitude +
                    "&key=" + apiKey;  // Replace with your actual API key


            // Set click listener for the static map to open Google Maps app
            staticMapImageView.setOnClickListener(v -> {
                // Create a Uri from the latitude and longitude
                Uri gmmIntentUri = Uri.parse("geo:" + propertyLatitude + "," + propertyLongitude + "?q=" + propertyLatitude + "," + propertyLongitude);

                // Create an Intent to open Google Maps app
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                // Check if there's an app to handle the intent
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // Handle the case where Google Maps app is not installed
                    // You can show a toast or a dialog to inform the user
                    // Alternatively, you can open the map in a browser using a web URL
                }
            });

            Picasso.get()
                    .load(staticMapUrl)
                    .placeholder(R.drawable.map_placeholder)  // Placeholder image while loading
                    .error(R.drawable.map_placeholder)  // Error image if loading fails
                    .into(staticMapImageView);



            if (UserType.LANDLORD.getValue() == property.getUserId().getUserTypeId()) {
                binding.reserveButton.setVisibility(View.INVISIBLE);
            }
            TextView landlordNameTextView = binding.landlordNameTextView;
            //landlordNameTextView.setText(property.getUserId());
            Picasso.get()
                    .load(property.getUserId().getImagePath())
                    .placeholder(R.drawable.profile_placeholder)
                    .fit()
                    .into(binding.landlordPhoto);
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Not needed for this implementation
            }

            @Override
            public void onPageSelected(int position) {
                updateImageCountText(position + 1, propertyImages.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Not needed for this implementation
            }
        });
    }

    private void updateImageCountText(int currentImage, int totalImages) {
        String countText = getString(R.string.image_count_format, currentImage, totalImages);
        imageCountTextView.setText(countText);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    private String getGoogleMapsApiKey() {
        try {
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.config);

            Properties properties = new Properties();
            properties.load(inputStream);

            return properties.getProperty("GOOGLE_MAPS_API_KEY");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the Google Maps intent
        if (requestCode == 1) { // Use the same request code as in startActivityForResult
            // Handle the result as needed
            if (resultCode == RESULT_OK) {
                // User returned successfully from Google Maps
                // Add any additional logic here
            } else {
                // Handle other possible results or errors
            }
        }
    }
}