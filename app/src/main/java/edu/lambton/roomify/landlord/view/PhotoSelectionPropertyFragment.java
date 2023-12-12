package edu.lambton.roomify.landlord.view;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.lambton.roomify.databinding.FragmentPhotoSelectionPropertyBinding;
import edu.lambton.roomify.landlord.model.Picture;
import edu.lambton.roomify.landlord.view.questionnaire.adapter.PropertyPictureRVAdapter;

public class PhotoSelectionPropertyFragment extends Fragment {


    private FragmentPhotoSelectionPropertyBinding binding;
    private Uri tempImageUri = null;
    private PropertyPictureRVAdapter propertyPictureRVAdapter;
    private final List<Picture> myPictures = new ArrayList<>();
    private Button uploadPictureButton;
    private Button takePhotoButton;
    private RecyclerView picturesThumbnailRV;

    private OnPhotoAddedListener onPhotoAddedListener;

    private final ActivityResultLauncher<PickVisualMediaRequest> selectPictureLauncher = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<>() {

        @Override
        public void onActivityResult(Uri result) {
            try {
                if (result != null) {
                    tempImageUri = result;

                    Picture picture = new Picture("content://media/" + tempImageUri.getPath(), new Date().getTime(), 0L);

                    myPictures.add(picture);
                    propertyPictureRVAdapter.notifyItemRangeChanged(0, myPictures.size());

                    if (myPictures.size() >= 3) {
                        notifyPhotoAdded(myPictures);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });

    private final ActivityResultLauncher<Uri> selectCameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
        if (result) {

            try {
                Picture picture = new Picture("content://media/" + tempImageUri.getPath(), new Date().getTime(), 0L);

                myPictures.add(picture);
                propertyPictureRVAdapter.notifyItemRangeChanged(0, myPictures.size());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPhotoSelectionPropertyBinding.inflate(inflater, container, false);
        uploadPictureButton = binding.addPhotoButton;
        takePhotoButton = binding.takePhotoButton;

        picturesThumbnailRV = binding.thumbnailPropertyRecycleView;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uploadPictureButton.setOnClickListener(this::addPhotoFromLibrary);
        takePhotoButton.setOnClickListener(this::takePhoto);

        propertyPictureRVAdapter = new PropertyPictureRVAdapter(myPictures);
        picturesThumbnailRV.setLayoutManager(new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false));
        picturesThumbnailRV.setAdapter(propertyPictureRVAdapter);

        if (myPictures.size() >= 2) {
            notifyPhotoAdded(myPictures);
        }

    }

    public void addPhotoFromLibrary(View view) {
        System.out.println("ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) " + ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA));
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            selectPictureLauncher.launch(new PickVisualMediaRequest());
        }
    }

    private void takePhoto(View view) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            ContentResolver cr = requireActivity().getContentResolver();
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

            tempImageUri = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            selectCameraLauncher.launch(tempImageUri);
        }
    }

    private void addPhotoFromLibrary() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            selectPictureLauncher.launch(new PickVisualMediaRequest());
        }
    }

    public void setOnPhotoAddedListener(OnPhotoAddedListener listener) {
        this.onPhotoAddedListener = listener;
    }

    // Method to be called when a photo is added
    private void notifyPhotoAdded(List<Picture> pictureList) {
        if (onPhotoAddedListener != null) {
            onPhotoAddedListener.onPhotoAdded();
            onPhotoAddedListener.onCallbackPhoto(pictureList);
        }
    }

    public interface OnPhotoAddedListener {
        void onPhotoAdded();

        void onCallbackPhoto(List<Picture> photoList);
    }
}