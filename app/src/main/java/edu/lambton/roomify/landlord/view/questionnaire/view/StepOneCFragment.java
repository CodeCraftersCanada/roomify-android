package edu.lambton.roomify.landlord.view.questionnaire.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.FragmentStep1CBinding;

public class StepOneCFragment extends Fragment {

    private FragmentStep1CBinding binding;
    private String selectedOption = "";
    private OnPlaceSelectedListener onPlaceSelectedListener; // Callback interface

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStep1CBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // CardView for the options - Entire House
        CardView cardEntireHouse = rootView.findViewById(R.id.cardEntireHouse);
        // CardView for the options - Room
        CardView cardRoom = rootView.findViewById(R.id.cardRoom);
        // CardView for the options - Shared Room
        CardView cardSharedRoom = rootView.findViewById(R.id.cardSharedRoom);

        // Set click listeners for the cards
        cardEntireHouse.setOnClickListener(v -> onCardClicked(cardEntireHouse, "entireHouse"));
        cardRoom.setOnClickListener(v -> onCardClicked(cardRoom, "room"));
        cardSharedRoom.setOnClickListener(v -> onCardClicked(cardSharedRoom, "sharedRoom"));

        return rootView;
    }

    private void onCardClicked(@NonNull CardView cardView, String option) {
        // Reset the background color of all cards
        resetCardBackgrounds();

        // Highlight the clicked card
        cardView.setCardBackgroundColor(getResources().getColor(R.color.card_selected_color));

        // Update the selected option
        selectedOption = option;

        // Notify the callback interface about the selected option
        if (onPlaceSelectedListener != null) {
            onPlaceSelectedListener.onPlaceSelected(selectedOption);
        }
    }

    private void resetCardBackgrounds() {
        // Reset the background color of all cards
        CardView cardEntireHouse = binding.cardEntireHouse;
        CardView cardRoom = binding.cardRoom;
        CardView cardSharedRoom = binding.cardSharedRoom;

        cardEntireHouse.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        cardRoom.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        cardSharedRoom.setCardBackgroundColor(getResources().getColor(android.R.color.white));
    }

    // Interface for the callback
    public interface OnPlaceSelectedListener {
        void onPlaceSelected(String selectedPlace);
    }

    public void setOnPlaceSelectedListener(OnPlaceSelectedListener listener) {
        this.onPlaceSelectedListener = listener;
    }
}
