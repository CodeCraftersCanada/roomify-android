package edu.lambton.roomify.landlord.view;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.lambton.roomify.auth.landlord.view.LandlordLoginActivity;
import edu.lambton.roomify.common.UserType;
import edu.lambton.roomify.databinding.FragmentProfileLandlordBinding;
import edu.lambton.roomify.landlord.model.User;
import edu.lambton.roomify.landlord.viewmodel.UserLandlordViewModel;
import edu.lambton.roomify.landlord.viewmodel.UserLandlordViewModelFactory;

public class ProfileLandlordFragment extends Fragment {

    FragmentProfileLandlordBinding binding;

    private EditText editFullNameEditText, collegeEditTextView, phoneEditTextView, addressEditTextView;

    private MaterialButton saveProfileButton, editProfileButton, updatePhotoButton, logoutButton;

    private UserLandlordViewModel userLandlordViewModel;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private StorageReference storageRef;
    private CircleImageView profileImage;
    private Uri tempImageUri = null;

    private final ActivityResultLauncher<Uri> selectCameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
        if (result) {

            try {
                String picturePath = "content://media/" + tempImageUri.getPath();

                Picasso.get().load(picturePath).resize(300, 300).centerInside().into(profileImage);
                profileImage.setDrawingCacheEnabled(true);
                profileImage.buildDrawingCache(true);

                new Thread(() -> {

                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (profileImage.getDrawable() != null) {
                        Bitmap bitmap = ((BitmapDrawable) profileImage.getDrawable()).getBitmap();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        byte[] data = outputStream.toByteArray();

                        StorageReference photosRef = storageRef.child("photos/" + currentUser.getUid());
                        UploadTask uploadTask = photosRef.putBytes(data);

                        uploadTask.addOnFailureListener(exception -> {
                            // Handle unsuccessful uploads
                            Toast.makeText(requireContext(), "Photo error uploading occurred", Toast.LENGTH_SHORT).show();
                        }).addOnSuccessListener(taskSnapshot -> {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            Toast.makeText(requireContext(), "Photo uploaded", Toast.LENGTH_SHORT).show();
                        });


                        uploadTask.continueWithTask(task -> {
                            if (!task.isSuccessful()) {
                                throw Objects.requireNonNull(task.getException());
                            }
                            // Continue with the task to get the download URL
                            return photosRef.getDownloadUrl();
                        }).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Uri photoDownloadUri = task.getResult();
                                UserProfileChangeRequest profileChangeNameRequest = new UserProfileChangeRequest.Builder()
                                        .setPhotoUri(photoDownloadUri)
                                        .build();

                            } else {
                                // Handle failures
                                Log.e(this.getClass().getName(), "Error fetching the photo URL");
                            }
                        });
                    }
                }).start();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });

    private final ActivityResultLauncher<PickVisualMediaRequest> selectPictureLauncher = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<>() {

        @Override
        public void onActivityResult(Uri result) {
            try {
                if (result != null) {
                    tempImageUri = result;
                    String picturePath = "content://media/" + result.getPath();
                    System.out.println(picturePath);

                    Picasso.get().load(picturePath).resize(300, 300).centerInside().into(profileImage);

                    profileImage.setDrawingCacheEnabled(true);
                    profileImage.buildDrawingCache(true);

                    new Thread(() -> {

                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        if (profileImage.getDrawable() != null) {
                            Bitmap bitmap = ((BitmapDrawable) profileImage.getDrawable()).getBitmap();
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            byte[] data = outputStream.toByteArray();

                            StorageReference photosRef = storageRef.child("avatar/" + mAuth.getUid());
                            UploadTask uploadTask = photosRef.putBytes(data);

                            uploadTask.addOnFailureListener(exception -> {
                                // Handle unsuccessful uploads
                            }).addOnSuccessListener(taskSnapshot -> {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            });


                            uploadTask.continueWithTask(task -> {
                                if (!task.isSuccessful()) {
                                    throw Objects.requireNonNull(task.getException());
                                }
                                // Continue with the task to get the download URL
                                return photosRef.getDownloadUrl();
                            }).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Uri photoDownloadUri = task.getResult();

                                    tempImageUri = photoDownloadUri;
                                    System.out.println("Photo PATH: " + tempImageUri);
                                    /*UserProfileChangeRequest profileChangeNameRequest = new UserProfileChangeRequest.Builder()
                                            .setPhotoUri(photoDownloadUri)
                                            .build();*/

                                } else {
                                    // Handle failures
                                    Log.e(this.getClass().getName(), "Error fetching the photo URL");
                                }
                            });
                        }
                    }).start();


                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileLandlordBinding.inflate(getLayoutInflater(), container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        binding.fullNameLandlord.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());
        binding.emailLandlord.setText(Objects.requireNonNull(mAuth.getCurrentUser().getEmail()));

        userLandlordViewModel = new ViewModelProvider(getViewModelStore(), new UserLandlordViewModelFactory(requireActivity().getApplication())).get(UserLandlordViewModel.class);

        profileImage = binding.profileAvatar;

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton callMeButton = binding.callMeButton;
        editProfileButton = binding.editProfileButton;
        editFullNameEditText = binding.editFullName;
        collegeEditTextView = binding.collegeEditTextView;
        phoneEditTextView = binding.phoneEditTextView;
        addressEditTextView = binding.addressEditTextView;
        updatePhotoButton = binding.updatePhotoButton;

        saveProfileButton = binding.saveProfileButton;
        saveProfileButton.setOnClickListener(this::saveEditedProfile);

        editProfileButton.setOnClickListener(this::toggleToEditProfile);

        updatePhotoButton.setOnClickListener(this::updatePhotoOption);

        callMeButton.setOnClickListener(this::callNumber);

        logoutButton = binding.logoutButton;
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        userLandlordViewModel.loadProfileInfo(mAuth.getUid()).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    binding.fullNameLandlord.setText(user.getFullName());
                    binding.collegeTextView.setText(user.getCollege());
                    binding.emailLandlord.setText(user.getEmail());
                    binding.phoneTextView.setText(user.getPhone());
                    binding.addressTextView.setText(user.getAddress());

                    if (!Objects.equals(user.getImagePath(), "")) {
                        Picasso.get().load(user.getImagePath()).resize(300, 300).centerInside().into(profileImage);
                    }
                }
            }
        });
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();

        // Redirect to login screen or another activity
        Intent intent = new Intent(getActivity(), LandlordLoginActivity.class);
        startActivity(intent);
        getActivity().finish(); // Close the current activity
    }

    private void updatePhotoOption(View view) {
        addPhotoFromLibrary();
    }

    private void saveEditedProfile(View view) {
        toggleEditMode();
    }

    private void toggleToEditProfile(View view) {
        toggleEditMode();
    }

    private void callNumber(View view) {

        String phoneNumber = "4372637254";

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireContext(), "No app found to handle the call", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleEditMode() {
        boolean isInEditMode = editFullNameEditText.getVisibility() == View.VISIBLE;

        if (isInEditMode) {
            // Save the edited information and switch back to TextView mode
            saveProfileButton.setVisibility(View.VISIBLE);
            editProfileButton.setVisibility(View.INVISIBLE);

            // Example: Save Full Name
            String editedFullName = editFullNameEditText.getText().toString();
            String editedCollege = collegeEditTextView.getText().toString();
            String editedPhone = phoneEditTextView.getText().toString();
            String editedAddress = addressEditTextView.getText().toString();

            // Update the corresponding TextView
            TextView fullNameTextView = binding.fullNameLandlord;
            fullNameTextView.setText(editedFullName);

            TextView collegeTextView = binding.collegeTextView;
            collegeTextView.setText(editedCollege);

            TextView phoneTextView = binding.phoneTextView;
            phoneTextView.setText(editedPhone);

            TextView addressTextView = binding.addressTextView;
            addressTextView.setText(editedAddress);

            User user = new User(null, mAuth.getUid(), UserType.LANDLORD.getValue(), editedFullName, Objects.requireNonNull(mAuth.getCurrentUser()).getEmail(), editedPhone, editedCollege, editedAddress, tempImageUri != null ? tempImageUri.toString() : "", 0, 0);

            updateUser(user);

            // Switch back to TextView mode
            editFullNameEditText.setVisibility(View.INVISIBLE);
            collegeEditTextView.setVisibility(View.INVISIBLE);
            phoneEditTextView.setVisibility(View.INVISIBLE);
            addressEditTextView.setVisibility(View.INVISIBLE);

            fullNameTextView.setVisibility(View.VISIBLE);
            collegeTextView.setVisibility(View.VISIBLE);
            phoneTextView.setVisibility(View.VISIBLE);
            addressTextView.setVisibility(View.VISIBLE);

        } else {
            saveProfileButton.setVisibility(View.INVISIBLE);
            editProfileButton.setVisibility(View.VISIBLE);
            // Switch to EditText mode
            editFullNameEditText.setVisibility(View.VISIBLE);
            collegeEditTextView.setVisibility(View.VISIBLE);
            phoneEditTextView.setVisibility(View.VISIBLE);
            addressEditTextView.setVisibility(View.VISIBLE);

            TextView fullNameTextView = binding.fullNameLandlord;
            fullNameTextView.setVisibility(View.INVISIBLE);

            TextView collegeTextView = binding.collegeTextView;
            collegeTextView.setVisibility(View.INVISIBLE);

            TextView phoneTextView = binding.phoneTextView;
            phoneTextView.setVisibility(View.INVISIBLE);

            TextView addressTextView = binding.addressTextView;
            addressTextView.setVisibility(View.INVISIBLE);

            // Set the current information to the EditText
            editFullNameEditText.setText(fullNameTextView.getText().toString());
            collegeEditTextView.setText(collegeTextView.getText().toString());
            phoneEditTextView.setText(phoneTextView.getText().toString());
            addressEditTextView.setText(addressTextView.getText().toString());

        }
    }

    void updateUser(User user) {
        userLandlordViewModel.updateUser(user);
    }

    public void addPhotoFromLibrary() {
        System.out.println("ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) " + ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA));
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            selectPictureLauncher.launch(new PickVisualMediaRequest());
        }
    }

    public void takePhoto() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            ContentResolver cr = requireContext().getContentResolver();
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

            tempImageUri = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            selectCameraLauncher.launch(tempImageUri);
        }
    }
}