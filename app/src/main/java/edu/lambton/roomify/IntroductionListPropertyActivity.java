package edu.lambton.roomify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

import edu.lambton.roomify.databinding.ActivityIntroductionListPropertyBinding;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.view.PropertyListingQuestionaryActivity;

public class IntroductionListPropertyActivity extends AppCompatActivity implements PropertyListingQuestionaryActivity.PropertyCreationCallback {

    private TextView pricingLabel;
    private TextView exampleEstimation;
    private SeekBar priceSeekBar;
    private static final int REQUEST_CODE_PROPERTY_QUESTIONARY = 1;

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
        Intent startQuestionnaire = new Intent(this, PropertyListingQuestionaryActivity.class);
        // Attach the callback to the child activity
        startActivityForResult(startQuestionnaire, REQUEST_CODE_PROPERTY_QUESTIONARY);
    }

    @Override
    public void onPropertyCreated(Property property) {
        // Handle the property creation callback here
        // You can perform actions like updating UI or navigating to another activity
        System.out.println("Property created in IntroductionListPropertyActivity: " + property);

        // Finish the current activity (PropertyListingQuestionaryActivity)
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PROPERTY_QUESTIONARY && resultCode == RESULT_OK) {
            // Property creation successful, close the parent activity
            finish();
        }
    }
}