package edu.lambton.roomify.landlord.view;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import edu.lambton.roomify.common.PropertyStatus;
import edu.lambton.roomify.databinding.ActivityPropertyListingQuestionaryBinding;
import edu.lambton.roomify.landlord.dto.PropertyPhotoRequest;
import edu.lambton.roomify.landlord.model.Address;
import edu.lambton.roomify.landlord.model.Picture;
import edu.lambton.roomify.landlord.model.PlaceRowOption;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.view.questionnaire.adapter.QuestionnairePagerAdapter;
import edu.lambton.roomify.landlord.viewmodel.ProperetyLandlordViewModelFactory;
import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class PropertyListingQuestionaryActivity extends AppCompatActivity {

    private ActivityPropertyListingQuestionaryBinding binding;
    private ViewPager viewPager;
    private QuestionnairePagerAdapter pagerAdapter;
    private Button btnNext, btnPrevious, btnFinish;

    private List<Fragment> questionnaireFragments;
    private ContentLoadingProgressBar progressBar;

    private Property property;
    private PropertyCreationCallback propertyCreationCallback;
    private final List<Picture> pictureList = new ArrayList<>();

    private final Hashtable<String, String> photosPath = new Hashtable<>();
    private PropertyLandlordViewModel propertyLandlordViewModel;

    private final List<PropertyPhotoRequest> propertyPhotoRequestList = new ArrayList<>();
    private CountDownLatch photoUploadLatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPropertyListingQuestionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager = binding.viewPager;
        btnNext = binding.btnNext;
        btnPrevious = binding.btnPrevious;
        btnFinish = binding.btnFinish;

        progressBar = binding.progressQuestionnaire;

        propertyLandlordViewModel = new ViewModelProvider(getViewModelStore(), new ProperetyLandlordViewModelFactory(getApplication())).get(PropertyLandlordViewModel.class);

        // List of Fragments for Questionnaire
        questionnaireFragments = new ArrayList<>();
        questionnaireFragments.add(new ListingOneAFragment());
        questionnaireFragments.add(new ListingOneCFragment());
        questionnaireFragments.add(new PhotoSelectionPropertyFragment());


        pagerAdapter = new QuestionnairePagerAdapter(getSupportFragmentManager(), questionnaireFragments);
        viewPager.setAdapter(pagerAdapter);

        // Set initial button visibility
        updateButtonVisibility();


        // Set the listener for the first fragment
        if (questionnaireFragments.get(0) instanceof ListingOneAFragment) {
            ((ListingOneAFragment) questionnaireFragments.get(0))
                    .setOnOptionSelectedListener(this::onOptionSelected);

        }

        if (questionnaireFragments.get(1) instanceof ListingOneCFragment) {
            ((ListingOneCFragment) questionnaireFragments.get(1))
                    .setOnOptionSelectedListener(this::onAddressSelected);
        }

        if (questionnaireFragments.get(questionnaireFragments.size() - 1) instanceof PhotoSelectionPropertyFragment) {
            ((PhotoSelectionPropertyFragment) questionnaireFragments.get(questionnaireFragments.size() - 1))
                    .setOnPhotoAddedListener(new PhotoSelectionPropertyFragment.OnPhotoAddedListener() {
                        @Override
                        public void onPhotoAdded() {
                            closeViewOnPhotoAdded();
                        }

                        @Override
                        public void onCallbackPhoto(List<Picture> photoList) {
                            pictureList.addAll(photoList);
                        }
                    });
        }


        btnNext.setOnClickListener(this::nextQuestion);

        btnPrevious.setOnClickListener(this::previousQuestion);

        btnFinish.setOnClickListener(this::createProperty);


    }


    private PlaceRowOption selectedOption;
    private Address selectedAddress;

    private void uploadPhotosToFirebaseStorage() {
        // Initialize the latch with the number of photos to upload
        photoUploadLatch = new CountDownLatch(pictureList.size());

        // Iterate through the list of photos and upload each one
        for (Picture photo : pictureList) {
            // Upload the photo to Firebase Storage
            // You need to implement the Firebase Storage upload logic here
            // Refer to Firebase Storage documentation for details
            System.out.println("PHOTO TO BE UPLOADED: " + photo.id() + " - " + photo.path());
            uploadPhoto(photo);
        }

        // Wait for all photo uploads to complete before proceeding
        try {
            if (photoUploadLatch.await(5, TimeUnit.SECONDS)) {
                // All photos uploaded successfully
                // Continue with property creation
                createProperty(null);
            } else {
                // Timeout occurred, handle appropriately
                Log.e(this.getClass().getName(), "Photo upload timeout");
            }
        } catch (InterruptedException e) {
            // Handle interruption
            Log.e(this.getClass().getName(), "Photo upload interrupted", e);
        }
    }

    private void uploadPhoto(@NonNull Picture photo) {
        // Example: Upload the photo to Firebase Storage using its URI
        // This is a simplified example, make sure to handle exceptions and use proper Firebase Storage APIs
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference photoRef = storageRef.child("property_photos/" + UUID.randomUUID().toString());

        // Assume 'photoUri' is the URI of the photo
        photoRef.putFile(Uri.parse(photo.path()))
                .addOnSuccessListener(taskSnapshot -> {
                    // Photo uploaded successfully
                    // Obtain the download URL and any other required information
                    String downloadUrl = Objects.requireNonNull(taskSnapshot.getUploadSessionUri()).toString();
                    String photoId = photoRef.getName();

                    System.out.println("PHOTOS PATH: " + downloadUrl);
                    photosPath.put(photoId, downloadUrl);
                    PropertyPhotoRequest propertyPhotoRequest = new PropertyPhotoRequest("", 1, downloadUrl);

                    propertyPhotoRequestList.add(propertyPhotoRequest);

                    // Count down the latch to indicate that this photo upload is complete
                    photoUploadLatch.countDown();
                    // Now you can use downloadUrl and photoId as needed
                    // For example, store them in the database along with other property details
                })
                .addOnFailureListener(exception -> {
                    // Handle failures
                    photoUploadLatch.countDown();
                    Log.e(this.getClass().getName(), "Error uploading photo", exception);
                });
    }

    private void createProperty(View view) {
        // TODO: Hardcode some values waiting for Elvin

        // Upload photos to Firebase Storage
        uploadPhotosToFirebaseStorage();

        if (selectedOption != null && selectedAddress != null) {
            property = new Property(
                    null,
                    "Description for Property 2",
                    selectedOption.propertyType(),
                    selectedOption.text(),
                    1,
                    "Entire Place",
                    3,
                    3,
                    4,
                    0,
                    500.0,
                    selectedAddress.subThoroughfare() + ", " + selectedAddress.thoroughfare() + " " + selectedAddress.subLocality() + " " + selectedAddress.featureName(),
                    "",
                    selectedAddress.city(),
                    selectedAddress.province(),
                    selectedAddress.country(),
                    selectedAddress.postalCode(),
                    selectedAddress.latitude(),
                    selectedAddress.longitude()

                    // Add other property attributes based on your design
            );

            property.setPropertyStatusId(PropertyStatus.PENDING.getStatusId());

            System.out.println("CREATED PROPERTY: " + property);
            propertyLandlordViewModel.saveProperty(property, propertyPhotoRequestList);
            // Do something with the created Property object, e.g., save it to a database
            // property.saveToDatabase();

            // Notify the callback about the created property
            if (propertyCreationCallback != null) {
                propertyCreationCallback.onPropertyCreated(property);
            }

            // Set the result and finish the child activity
            setResult(RESULT_OK);

            // Finish the activity or navigate to the next screen
            finish();
        } else {
            // Handle the case where not all options are selected
            // You may show a message to the user or take appropriate action
            System.out.println("Not all options are selected");
        }
    }


    private void onOptionSelected(@NonNull PlaceRowOption selectedOption) {
        this.selectedOption = selectedOption;
        System.out.println("SELECTED PROPERTY: " + selectedOption.propertyType() + " - " + selectedOption.text());
    }

    private void onAddressSelected(@NonNull Address address) {
        this.selectedAddress = address;
        System.out.println("ADDRESS SELECTED: " + address.latitude() + " - " + address.longitude() + " - " + address.city());
    }

    public void closeViewOnPhotoAdded() {
        // Show the finish button when a photo is added on the last page
        btnFinish.setVisibility(View.VISIBLE);
    }

    private void previousQuestion(View view) {
        if (viewPager.getCurrentItem() > 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            updateButtonVisibility();
            updateProgressBar();
        }
    }

    private void nextQuestion(View view) {

        System.out.println("CURRENT FRAGMENT: " + (viewPager.getCurrentItem() == questionnaireFragments.size()));
        System.out.println(viewPager.getCurrentItem());
        System.out.println(questionnaireFragments.size());
        if (viewPager.getCurrentItem() < questionnaireFragments.size() - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            updateButtonVisibility();
            updateProgressBar();
        }
    }

    private void updateButtonVisibility() {
        if (viewPager.getCurrentItem() == 0) {
            btnPrevious.setVisibility(View.INVISIBLE);
        } else {
            btnPrevious.setVisibility(View.VISIBLE);
        }

        if (viewPager.getCurrentItem() == pagerAdapter.getCount() - 1) {
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        }
    }

    private void updateProgressBar() {
        int currentItem = viewPager.getCurrentItem();
        int totalItems = questionnaireFragments.size();
        int progress = (int) (((float) currentItem / totalItems) * 100);

        progressBar.setProgress(progress, true);
    }

    public void setPropertyCreationCallback(PropertyCreationCallback callback) {
        this.propertyCreationCallback = callback;
    }

    public interface PropertyCreationCallback extends Serializable {
        void onPropertyCreated(Property property);
    }
}
