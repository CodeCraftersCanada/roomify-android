package edu.lambton.roomify.landlord.view.questionnaire.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.FragmentStep1EBinding;

public class StepOneEFragment extends Fragment {

    private FragmentStep1EBinding binding;
    private int guestCount = 0;
    private int bedroomCount = 0;
    private int bedCount = 0;
    private int bathroomCount = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentStep1EBinding.inflate(inflater, container, false);

        View rootView = binding.getRoot();

        // Initialize Views
        TextView numGuestTextView = rootView.findViewById(R.id.numGuessButton);
        TextView numBedroomTextView = rootView.findViewById(R.id.numBedroomsTextView);
        TextView numBedTextView = rootView.findViewById(R.id.numBedsTextView);
        TextView numBathroomTextView = rootView.findViewById(R.id.numBathroomTextView);

        Button decrementGuestButton = rootView.findViewById(R.id.decrementGuestButton);
        Button incrementGuestButton = rootView.findViewById(R.id.incrementGuestButton);
        Button decrementBedroomButton = rootView.findViewById(R.id.decrementBedroomsButton);
        Button incrementBedroomButton = rootView.findViewById(R.id.incrementBedroomsButton);
        Button decrementBedButton = rootView.findViewById(R.id.decrementBedsButton);
        Button incrementBedButton = rootView.findViewById(R.id.incrementBedsButton);
        Button decrementBathroomButton = rootView.findViewById(R.id.decrementBathroomButton);
        Button incrementBathroomButton = rootView.findViewById(R.id.incrementBathroomButton);

        // Set initial values
        numGuestTextView.setText(String.valueOf(guestCount));
        numBedroomTextView.setText(String.valueOf(bedroomCount));
        numBedTextView.setText(String.valueOf(bedCount));
        numBathroomTextView.setText(String.valueOf(bathroomCount));

        // Set click listeners for increment and decrement buttons
        decrementGuestButton.setOnClickListener(v -> updateCount(numGuestTextView, -1, "guest"));
        incrementGuestButton.setOnClickListener(v -> updateCount(numGuestTextView, 1, "guest"));
        decrementBedroomButton.setOnClickListener(v -> updateCount(numBedroomTextView, -1, "bedroom"));
        incrementBedroomButton.setOnClickListener(v -> updateCount(numBedroomTextView, 1, "bedroom"));
        decrementBedButton.setOnClickListener(v -> updateCount(numBedTextView, -1, "bed"));
        incrementBedButton.setOnClickListener(v -> updateCount(numBedTextView, 1, "bed"));
        decrementBathroomButton.setOnClickListener(v -> updateCount(numBathroomTextView, -1, "bathroom"));
        incrementBathroomButton.setOnClickListener(v -> updateCount(numBathroomTextView, 1, "bathroom"));

        return rootView;
    }

    private void updateCount(@NonNull TextView textView, int change, String category) {
        int count = Integer.parseInt(textView.getText().toString()) + change;
        if (count >= 0) {
            textView.setText(String.valueOf(count));
            // Update corresponding variable based on the category
            switch (category) {
                case "guest" -> guestCount = count;
                case "bedroom" -> bedroomCount = count;
                case "bed" -> bedCount = count;
                case "bathroom" -> bathroomCount = count;
            }
        }
    }
}
