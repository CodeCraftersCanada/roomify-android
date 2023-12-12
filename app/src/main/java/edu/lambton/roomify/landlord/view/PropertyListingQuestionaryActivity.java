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

import edu.lambton.roomify.PropertyPriceListingFragment;
import edu.lambton.roomify.StepOneAFragment;
import edu.lambton.roomify.common.PropertyStatus;
import edu.lambton.roomify.databinding.ActivityPropertyListingQuestionaryBinding;
import edu.lambton.roomify.landlord.dto.PropertyPhotoRequest;
import edu.lambton.roomify.landlord.model.Address;
import edu.lambton.roomify.landlord.model.Picture;
import edu.lambton.roomify.landlord.model.PlaceRowOption;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.view.questionnaire.adapter.QuestionnairePagerAdapter;
import edu.lambton.roomify.landlord.view.questionnaire.view.StepOneCFragment;
import edu.lambton.roomify.landlord.view.questionnaire.view.StepOneEFragment;
import edu.lambton.roomify.landlord.view.questionnaire.view.StepThreeAFragment;
import edu.lambton.roomify.landlord.view.questionnaire.view.StepTwoAFragment;
import edu.lambton.roomify.landlord.view.questionnaire.view.StepTwoDFragment;
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
        questionnaireFragments.add(new StepOneAFragment()); //0
        questionnaireFragments.add(new ListingOneAFragment()); //1
        questionnaireFragments.add(new StepOneCFragment()); //2
        questionnaireFragments.add(new ListingOneCFragment()); //3
        questionnaireFragments.add(new StepOneEFragment()); //4
        questionnaireFragments.add(new StepTwoAFragment()); //5
        questionnaireFragments.add(new StepTwoDFragment()); // 6
        questionnaireFragments.add(new StepThreeAFragment()); //7
        questionnaireFragments.add(new PropertyPriceListingFragment()); //8
        questionnaireFragments.add(new PhotoSelectionPropertyFragment()); //9


        pagerAdapter = new QuestionnairePagerAdapter(getSupportFragmentManager(), questionnaireFragments);
        viewPager.setAdapter(pagerAdapter);

        // Set initial button visibility
        updateButtonVisibility();


        // Set the listener for the first fragment
        if (questionnaireFragments.get(1) instanceof ListingOneAFragment) {
            ((ListingOneAFragment) questionnaireFragments.get(1))
                    .setOnOptionSelectedListener(this::onOptionSelected);
        }

        if (questionnaireFragments.get(2) instanceof StepOneCFragment) {
            ((StepOneCFragment) questionnaireFragments.get(2))
                    .setOnPlaceSelectedListener(this::onPlaceSelected);
        }


        if (questionnaireFragments.get(3) instanceof ListingOneCFragment) {
            ((ListingOneCFragment) questionnaireFragments.get(3))
                    .setOnOptionSelectedListener(this::onAddressSelected);
        }

        if (questionnaireFragments.get(4) instanceof StepOneEFragment) {
            ((StepOneEFragment) questionnaireFragments.get(4))
                    .setOnPropertyFeatureListener(this::onFeaturesSelected);
        }

        if (questionnaireFragments.get(6) instanceof StepTwoDFragment) {
            ((StepTwoDFragment) questionnaireFragments.get(6))
                    .setOnDescriptionChangedListener(this::descriptionListener);
        }

        if (questionnaireFragments.get(8) instanceof PropertyPriceListingFragment) {
            ((PropertyPriceListingFragment) questionnaireFragments.get(8))
                    .setOnPriceChangedListener(this::onPriceChanged);
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

        btnFinish.setOnClickListener(v -> {
            uploadPhotosToFirebaseStorage();
        });


    }


    private String description;

    private void descriptionListener(String description) {
        this.description = description;
        System.out.println("DESCRIPTION: " + description);
    }

    private PlaceRowOption selectedOption;
    private Address selectedAddress;
    private int guestCount;
    private int bedroomCount;
    private int bedCount;
    private int bathroomCount;

    private double price;
    private String placeSelected;

    private void onPlaceSelected(String placeSelected) {
        this.placeSelected = placeSelected;
    }

    private void onPriceChanged(double newPrice) {
        this.price = newPrice;
    }

    private void uploadPhotosToFirebaseStorage() {
        // Track the number of photos to upload
        int totalPhotos = pictureList.size();

        // Counter for successful photo uploads
        final int[] successfulUploads = {0};

        // Iterate through the list of photos and upload each one
        for (Picture photo : pictureList) {
            uploadPhoto(photo, new PhotoUploadCallback() {
                @Override
                public void onSuccess(String photoId, String downloadUrl) {
                    // Photo uploaded successfully
                    photosPath.put(photoId, downloadUrl);
                    propertyPhotoRequestList.add(new PropertyPhotoRequest(null, 0, downloadUrl));
                    // Increase the counter for successful uploads
                    successfulUploads[0]++;

                    // Check if all photos have been uploaded
                    if (successfulUploads[0] == totalPhotos) {
                        // All photos uploaded successfully
                        // Continue with property creation
                        continuePropertyCreation();
                    }
                }

                @Override
                public void onFailure(Exception exception) {
                    // Handle failures during photo upload
                    Log.e(this.getClass().getName(), "Error uploading photo", exception);
                }
            });
        }
    }

    private void uploadPhoto(@NonNull Picture photo, PhotoUploadCallback callback) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference photoRef = storageRef.child("property_photos/" + UUID.randomUUID().toString());

        photoRef.putFile(Uri.parse(photo.path()))
                .addOnSuccessListener(taskSnapshot -> {
                    // Photo uploaded successfully
                    photoRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String downloadUrl = uri.toString();
                        String photoId = photoRef.getName();

                        callback.onSuccess(photoId, downloadUrl);
                    }).addOnFailureListener(exception -> {
                        // Handle failures when getting download URL
                        callback.onFailure(exception);
                    });
                })
                .addOnFailureListener(exception -> {
                    // Handle failures during photo upload
                    callback.onFailure(exception);
                });
    }

    private void continuePropertyCreation() {
        // TODO: Hardcode some values waiting for Elvin

        // Upload photos to Firebase Storage
        if (selectedOption != null && selectedAddress != null) {
            property = new Property(
                    null,
                    description,
                    selectedOption.propertyType(),
                    selectedOption.text(),
                    1,
                    placeSelected,
                    guestCount,
                    bedroomCount,
                    bedCount,
                    0,
                    price,
                    selectedAddress.subThoroughfare() + ", " + selectedAddress.thoroughfare() + " " + selectedAddress.subLocality() + " " + selectedAddress.featureName(),
                    null,
                    selectedAddress.city(),
                    selectedAddress.province(),
                    selectedAddress.country(),
                    selectedAddress.postalCode(),
                    selectedAddress.latitude(),
                    selectedAddress.longitude()

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
            System.out.println("Not all options are selected");
        }


    }


    private void onOptionSelected(@NonNull PlaceRowOption selectedOption) {
        this.selectedOption = selectedOption;
        System.out.println("SELECTED PROPERTY: " + selectedOption.propertyType() + " - " + selectedOption.text());
    }

    private void onFeaturesSelected(int guestCount, int bedroomCount, int bedCount, int bathroomCount) {
        String features = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            features = """
                    Guest #: %d
                    Bedroom #: %d
                    Bed #: %d
                    Bathroom #: %d
                    """.formatted(guestCount, bedroomCount, bedCount, bathroomCount);
        }
        this.guestCount = guestCount;
        this.bedroomCount = bedroomCount;
        this.bedCount = bedCount;
        this.bathroomCount = bathroomCount;
        System.out.println(features);
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

    interface PhotoUploadCallback {
        void onSuccess(String photoId, String downloadUrl);

        void onFailure(Exception exception);
    }
}
