package edu.lambton.roomify.landlord.view;

import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.lambton.roomify.R;
import edu.lambton.roomify.landlord.model.PlaceRowOption;
import edu.lambton.roomify.landlord.view.questionnaire.adapter.DescribePlacesRVAdapter;


public class ListingOneAFragment extends Fragment implements DescribePlacesRVAdapter.OnSelectionHandlerListener {

    private DescribePlacesRVAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing_one_a, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycleDescriptionPlace);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        List<PlaceRowOption> placeOptions = getPlaceOptions(); // Implement this method to get your options
        adapter = new DescribePlacesRVAdapter(placeOptions, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<PlaceRowOption> getPlaceOptions() {
        List<PlaceRowOption> placeOptions = new ArrayList<>();

        PlaceRowOption placeRowOptionOne = new PlaceRowOption(AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_add_business_24), "Home");
        PlaceRowOption placeRowOptionTwo = new PlaceRowOption(AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_chat_24), "Chat");
        PlaceRowOption placeRowOptionThree = new PlaceRowOption(AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_lock_outline_24), "Lock");
        PlaceRowOption placeRowOptionFour = new PlaceRowOption(AppCompatResources.getDrawable(requireContext(), R.drawable.baseline_search_24), "Search");
        placeOptions.add(placeRowOptionOne);
        placeOptions.add(placeRowOptionTwo);
        placeOptions.add(placeRowOptionThree);
        placeOptions.add(placeRowOptionFour);

        return placeOptions;
    }

    @Override
    public void onSelectOption(int position) {
        // Handle option selection
    }
}