package edu.lambton.roomify.landlord.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.ActivityPropertyInfoBinding;
import edu.lambton.roomify.landlord.dto.PropertyResponse;
import edu.lambton.roomify.landlord.dto.PropertyResponseInfo;
import edu.lambton.roomify.landlord.model.Picture;
import edu.lambton.roomify.landlord.view.adapter.ImageSliderAdapter;
import edu.lambton.roomify.landlord.viewmodel.ProperetyLandlordViewModelFactory;
import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class PropertyInfoActivity extends AppCompatActivity {

    private ActivityPropertyInfoBinding binding;
    private PropertyLandlordViewModel propertyLandlordViewModel;
    private final List<PropertyResponseInfo.PhotoDTO> propertyImages = new ArrayList<>();
    private ImageSliderAdapter imageSliderAdapter;

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
        ViewPager viewPager = binding.viewPagerImages;


        propertyLandlordViewModel = new ViewModelProvider(getViewModelStore(), new ProperetyLandlordViewModelFactory(getApplication())).get(PropertyLandlordViewModel.class);

        String propertyId = PropertyInfoActivityArgs.fromBundle(getIntent().getExtras()).getPropertyId();
        System.out.println("PASSED FROM: " + propertyId);

        imageSliderAdapter = new ImageSliderAdapter(this, propertyImages);
        viewPager.setAdapter(imageSliderAdapter);

        propertyLandlordViewModel.getPropertyInfo(propertyId).observe(this, propertyResponse -> {
            runOnUiThread(() -> {
                propertyImages.clear();

                List<PropertyResponseInfo.PhotoDTO> newPhotos = propertyResponse.getProperty().getPhotos();
                propertyImages.addAll(newPhotos);

                imageSliderAdapter.notifyDataSetChanged();

            });

            PropertyResponseInfo.PropertyDTO property = propertyResponse.getProperty();
            binding.price.setText(String.valueOf(property.getPrice().getNumberDecimal()));
            binding.description.setText(property.getDescription());

        });
        
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