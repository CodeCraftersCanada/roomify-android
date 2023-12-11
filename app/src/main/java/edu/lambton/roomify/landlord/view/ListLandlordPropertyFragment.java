package edu.lambton.roomify.landlord.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.R;
import edu.lambton.roomify.databinding.FragmentListLandlordPropertyBinding;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.view.adapter.PropertyListLandlordRVAdapter;
import edu.lambton.roomify.landlord.viewmodel.ProperetyLandlordViewModelFactory;
import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class ListLandlordPropertyFragment extends Fragment implements PropertyListLandlordRVAdapter.OnPropertyCardListener {

    private FragmentListLandlordPropertyBinding binding;
    private ExtendedFloatingActionButton listNewPropertyButton;

    private RecyclerView propertyListRecycleView;
    private PropertyListLandlordRVAdapter adapter;

    private PropertyLandlordViewModel propertyLandlordViewModel;
    private final List<Property> propertyList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListLandlordPropertyBinding.inflate(inflater, container, false);
        listNewPropertyButton = binding.listNewPropertyButton;

        propertyListRecycleView = binding.propertyListRecycleView;

        propertyLandlordViewModel = new ViewModelProvider(getViewModelStore(), new ProperetyLandlordViewModelFactory(requireActivity().getApplication())).get(PropertyLandlordViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listNewPropertyButton.setOnClickListener(this::startQuestionnaire);

        propertyListRecycleView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new PropertyListLandlordRVAdapter(requireContext(), propertyList, this);
        propertyListRecycleView.setAdapter(adapter);

        propertyLandlordViewModel.getAllProperties().observe(getViewLifecycleOwner(), propertiesResult -> {
            propertyList.clear();
            propertyList.addAll(propertiesResult);
            adapter.notifyDataSetChanged();
        });


    }

    private void startQuestionnaire(View view) {
        Intent startQuestionnaire = new Intent(requireContext(), IntroductionListPropertyActivity.class);
        startActivity(startQuestionnaire);
    }

    @Override
    public void onPropertyCardHandle(View view) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main_landlord);
        navController.navigate(R.id.action_listPropertyFragment_to_propertyInfoActivity);
    }
}