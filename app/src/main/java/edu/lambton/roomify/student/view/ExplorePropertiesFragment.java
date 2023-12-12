package edu.lambton.roomify.student.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.lambton.roomify.databinding.FragmentExplorePropertiesBinding;
import edu.lambton.roomify.student.dto.StudentPropertyResponse;
import edu.lambton.roomify.student.view.adapter.ExplorePropertyRVAdapter;
import edu.lambton.roomify.student.viewmodel.ExplorePropertyViewModel;
import edu.lambton.roomify.student.viewmodel.ExplorePropertyViewModelFactory;

public class ExplorePropertiesFragment extends Fragment {

    private FragmentExplorePropertiesBinding binding;
    private RecyclerView propertyListReviewView;
    private ExplorePropertyRVAdapter adapter;
    private ExplorePropertyViewModel explorePropertyViewModel;
    private final List<StudentPropertyResponse.PropertyDTO> studentPropertyResponses = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentExplorePropertiesBinding.inflate(inflater, container, false);
        propertyListReviewView = binding.propertyList;

        propertyListReviewView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ExplorePropertyRVAdapter(studentPropertyResponses);
        propertyListReviewView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        explorePropertyViewModel = new ViewModelProvider(getViewModelStore(), new ExplorePropertyViewModelFactory(requireActivity().getApplication()))
                .get(ExplorePropertyViewModel.class);

        explorePropertyViewModel.getExploreAllProperties().observe(getViewLifecycleOwner(), studentPropertyResponse -> {

            if (studentPropertyResponse != null && studentPropertyResponse.getProperties().size() > 0) {

                List<StudentPropertyResponse.PropertyDTO> propertiesDto = studentPropertyResponse.getProperties();

                studentPropertyResponses.addAll(propertiesDto);

                System.out.println("INFO RETURNED:  " + propertiesDto.size());
                adapter.notifyItemMoved(0, studentPropertyResponses.size());
            }
        });
    }
}