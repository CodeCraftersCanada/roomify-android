package edu.lambton.roomify.landlord.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

import edu.lambton.roomify.auth.landlord.dto.UserResponse;
import edu.lambton.roomify.landlord.dto.PropertyPhotoRequest;
import edu.lambton.roomify.landlord.dto.PropertyRequest;
import edu.lambton.roomify.landlord.dto.PropertyResponse;
import edu.lambton.roomify.landlord.dto.PropertyResponseComplete;
import edu.lambton.roomify.landlord.dto.PropertyResponseInfo;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.repository.PropertyRepository;
import edu.lambton.roomify.landlord.services.ApiService;
import edu.lambton.roomify.landlord.services.RoomifyApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyLandlordViewModel extends ViewModel {
    private final MutableLiveData<PropertyResponse> responseData = new MutableLiveData<>();
    private final PropertyRepository repository;
    private final FirebaseAuth firebaseAuth;


    public PropertyLandlordViewModel(@NonNull Application application) {
        this.repository = new PropertyRepository(application, RoomifyApiClient.getApiService());
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public LiveData<List<Property>> getAllProperties() {
        return this.repository.getAllProperties();
    }

    public LiveData<PropertyResponseComplete> getAllPropertiesExternal() {
        return this.repository.getPropertiesExternal();
    }

    public LiveData<PropertyResponseInfo> getPropertyInfo(String id) {
        return repository.getPropertyInfo(id);
    }

    public void saveProperty(@NonNull Property property, List<PropertyPhotoRequest> propertiesPhotoRequest) {
        // Assuming userId is a String
        String userIdString = Objects.requireNonNull(firebaseAuth.getUid());

        ApiService apiService = RoomifyApiClient.getApiService();
        apiService.findUserById(userIdString).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()) {

                    assert response.body() != null;
                    PropertyRequest propertyRequest = new PropertyRequest(
                            response.body().getUser().getId(),
                            property.getPropertyStatusId(),
                            property.getName(),
                            property.description(),
                            property.propertyType(),
                            property.sharedType(),
                            property.sharedName(),
                            property.guestNumber(),
                            property.bedroomNumber(),
                            property.bedsNumber(),
                            property.bedroomLocked(),
                            property.price(),
                            property.address1(),
                            property.address2(),
                            property.city(),
                            property.province(),
                            property.country(),
                            property.postal_code(),
                            property.latitude(),
                            property.longitude()
                    );

                    postData(propertyRequest, propertiesPhotoRequest);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                System.err.println(t.getCause());
            }
        });


    }

    public void postData(PropertyRequest requestModel, List<PropertyPhotoRequest> propertyPhotosRequest) {

        repository.postData(requestModel, propertyPhotosRequest)
                .observeForever(responseData::setValue);
    }

    public void refreshProperties() {
        getAllProperties();
    }
}
