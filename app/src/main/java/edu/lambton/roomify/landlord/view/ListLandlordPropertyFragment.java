package edu.lambton.roomify.landlord.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.FragmentListLandlordPropertyBinding;
import edu.lambton.roomify.landlord.dto.PropertyResponseComplete;
import edu.lambton.roomify.landlord.view.adapter.PropertyListLandlordRVAdapter;
import edu.lambton.roomify.landlord.viewmodel.ProperetyLandlordViewModelFactory;
import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class ListLandlordPropertyFragment extends Fragment implements PropertyListLandlordRVAdapter.OnPropertyCardListener {

    private static final int CALL_PERMISSION_REQUEST_CODE = 1111;
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

        fetchPropertyData();

        adapter = new PropertyListLandlordRVAdapter(requireContext(), propertyList, this);
        propertyListRecycleView.setAdapter(adapter);
        return binding.getRoot();
    }

    private void fetchPropertyData() {
        propertyLandlordViewModel.getAllPropertiesExternal().observe(requireActivity(), propertiesResult -> {
            propertyList.clear();

            List<PropertyResponseComplete.Property> properties = propertiesResult.getProperties();

            propertyList.addAll(properties);


            adapter.notifyDataSetChanged();
        });
    }

    private void refreshProperties() {
        fetchPropertyData();

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

    @Override
    public void onPhoneCallHandle(String phoneNumber) {
        // Ensure the phone number is not null or empty
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            // Check if the CALL_PHONE permission is granted
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                // Create the intent to initiate a call
                Intent dialIntent = new Intent(Intent.ACTION_CALL);
                dialIntent.setData(Uri.parse("tel:" + phoneNumber));

                // Start the call directly without user confirmation
                startActivity(dialIntent);
            } else {
                // Request CALL_PHONE permission if not granted
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
            }
        } else {
            // Handle the case where the phone number is not valid
            Toast.makeText(requireContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
        }
    }
}