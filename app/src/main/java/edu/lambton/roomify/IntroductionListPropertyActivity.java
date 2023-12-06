package edu.lambton.roomify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

import edu.lambton.roomify.databinding.ActivityIntroductionListPropertyBinding;

public class IntroductionListPropertyActivity extends AppCompatActivity {

    private TextView pricingLabel;
    private TextView exampleEstimation;
    private SeekBar priceSeekBar;

    private ActivityIntroductionListPropertyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroductionListPropertyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pricingLabel = binding.pricingLabel;
        exampleEstimation = binding.exampleEstimation;
        priceSeekBar = binding.priceSeekerRange;

        Button roomifySetupButton = binding.roomifySetupButton;
        roomifySetupButton.setOnClickListener(this::listingPropertySetup);

        // Set a listener to the SeekBar
        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Calculate the estimated earnings based on the progress
                int estimatedEarnings = progress * 190; // Assuming $190 per night
                // Update the TextViews with the calculated values
                pricingLabel.setText(String.format(Locale.CANADA, "%,d CAD", estimatedEarnings));
                exampleEstimation.setText(String.format(Locale.CANADA, "%d nights at an estimated $190 CAD a night", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void listingPropertySetup(View view) {
        
    }
}