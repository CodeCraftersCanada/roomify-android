package edu.lambton.roomify.landlord.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.FragmentListLandlordPropertyBinding;
import edu.lambton.roomify.landlord.dto.PropertyResponseComplete;
import edu.lambton.roomify.landlord.dto.PropertyResponseInfo;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.view.adapter.PropertyListLandlordRVAdapter;
import edu.lambton.roomify.landlord.viewmodel.ProperetyLandlordViewModelFactory;
import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class ListLandlordPropertyFragment extends Fragment implements PropertyListLandlordRVAdapter.OnPropertyCardListener {

    private FragmentListLandlordPropertyBinding binding;
    private ExtendedFloatingActionButton listNewPropertyButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView propertyListRecycleView;
    private PropertyListLandlordRVAdapter adapter;

    private PropertyLandlordViewModel propertyLandlordViewModel;
    private final List<PropertyResponseComplete.Property> propertyList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListLandlordPropertyBinding.inflate(inflater, container, false);

        swipeRefreshLayout = binding.swipeRefreshLayout; // Add this line
        swipeRefreshLayout.setOnRefreshListener(this::refreshProperties); // Add this line

        listNewPropertyButton = binding.listNewPropertyButton;

        propertyListRecycleView = binding.propertyListRecycleView;
        listNewPropertyButton.setOnClickListener(this::startQuestionnaire);
        propertyListRecycleView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        propertyLandlordViewModel = new ViewModelProvider(getViewModelStore(), new ProperetyLandlordViewModelFactory(requireActivity().getApplication())).get(PropertyLandlordViewModel.class);

        propertyLandlordViewModel.getAllPropertiesExternal().observe(requireActivity(), propertiesResult -> {
            propertyList.clear();

            List<PropertyResponseComplete.Property> properties = propertiesResult.getProperties();

            propertyList.addAll(properties);



            adapter.notifyDataSetChanged();
        });

        adapter = new PropertyListLandlordRVAdapter(requireContext(), propertyList, this);
        propertyListRecycleView.setAdapter(adapter);
        return binding.getRoot();
    }

    private void refreshProperties() {
        propertyLandlordViewModel.refreshProperties();

        // Hide the refresh indicator after the refresh is complete
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void startQuestionnaire(View view) {
        Intent startQuestionnaire = new Intent(requireContext(), IntroductionListPropertyActivity.class);
        startActivity(startQuestionnaire);
    }

    @Override
    public void onPropertyCardHandle(String propertyId) {

        NavDirections action = ListLandlordPropertyFragmentDirections.actionListPropertyFragmentToPropertyInfoActivity(propertyId);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main_landlord).navigate(action);
    }
}