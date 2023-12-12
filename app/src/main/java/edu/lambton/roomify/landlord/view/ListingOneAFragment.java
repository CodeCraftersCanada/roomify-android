package edu.lambton.roomify.landlord.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.landlord.model.PlaceRowOption;
import edu.lambton.roomify.landlord.view.questionnaire.adapter.DescribePlacesRVAdapter;


public class ListingOneAFragment extends Fragment implements DescribePlacesRVAdapter.OnSelectionHandlerListener {

    private DescribePlacesRVAdapter adapter;
    List<PlaceRowOption> placeOptions;

    private OnOptionSelectedListener optionSelectedListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_one_a, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycleDescriptionPlace);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        placeOptions = getPlaceOptions();
        adapter = new DescribePlacesRVAdapter(placeOptions, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @NonNull
    private List<PlaceRowOption> getPlaceOptions() {
        List<PlaceRowOption> placeOptions = new ArrayList<>();

        PlaceRowOption placeRowOptionOne = new PlaceRowOption(1, AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_add_business_24), "Home", false);
        PlaceRowOption placeRowOptionTwo = new PlaceRowOption(2, AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_chat_24), "Apartment", false);
        PlaceRowOption placeRowOptionThree = new PlaceRowOption(3, AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_lock_outline_24), "Basement", false);
        PlaceRowOption placeRowOptionFour = new PlaceRowOption(4, AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_search_24), "Bedroom", false);

        placeOptions.add(placeRowOptionOne);
        placeOptions.add(placeRowOptionTwo);
        placeOptions.add(placeRowOptionThree);
        placeOptions.add(placeRowOptionFour);

        return placeOptions;
    }

    @Override
    public void onSelectOption(int position, boolean isSelected) {
        PlaceRowOption selectedOption = placeOptions.get(position);

        // Notify the listener about the selected option
        if (optionSelectedListener != null) {
            optionSelectedListener.onOptionSelected(selectedOption);
        }
    }

    public void setOnOptionSelectedListener(OnOptionSelectedListener listener) {
        this.optionSelectedListener = listener;
    }

    public interface OnOptionSelectedListener {
        void onOptionSelected(PlaceRowOption selectedOption);
    }

}