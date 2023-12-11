package edu.lambton.roomify.landlord.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import edu.lambton.roomify.auth.landlord.dto.UserResponse;
import edu.lambton.roomify.common.AppDatabase;
import edu.lambton.roomify.landlord.dao.PropertyDao;
import edu.lambton.roomify.landlord.dto.PropertyRequest;
import edu.lambton.roomify.landlord.dto.PropertyResponse;
import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyRepository {
    private final PropertyDao propertyDao;
    private final ApiService apiService;
    private final FirebaseAuth firebaseAuth;


    public PropertyRepository(Application application, ApiService apiService) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.apiService = apiService;
        propertyDao = db.propertyDao();
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public LiveData<List<Property>> getAllProperties() {
        MutableLiveData<List<Property>> propertiesList = new MutableLiveData<>();

        apiService.findUserById(firebaseAuth.getUid()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String id = response.body().getUser().getId();

                    // Observe the database query result and update propertiesList when it changes
                    LiveData<List<Property>> allPropertiesCreatedByMe = propertyDao.getAllPropertiesCreatedByMe(id);
                    Observer<List<Property>> observer = new Observer<>() {
                        @Override
                        public void onChanged(List<Property> properties) {
                            propertiesList.postValue(properties);

                            allPropertiesCreatedByMe.removeObserver(this);
                        }
                    };

                    allPropertiesCreatedByMe.observeForever(observer);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Handle failure, for example, log an error
                Log.e("PropertyViewModel", "Failed to fetch user data", t);
            }
        });

        return propertiesList;
    }

    public void save(Property property) {
        AppDatabase.databaseWriterExecutor.execute(() -> {

            propertyDao.saveProperty(property);
        });
    }

    public LiveData<PropertyResponse> postData(PropertyRequest requestModel) {
        MutableLiveData<PropertyResponse> result = new MutableLiveData<>();

        apiService.createProperty(requestModel).enqueue(new Callback<>() {

            @Override
            public void onResponse(@NonNull Call<PropertyResponse> call, @NonNull Response<PropertyResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        result.setValue(response.body());

                        Property propertyMapped = mapPropertyResponseToProperty(response.body());
                        System.out.println("RESULT FROM SERVER: " + propertyMapped);

                        assert propertyMapped != null;

                        propertyMapped.setUserID(requestModel.getUserId());

                        save(propertyMapped);

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                } else {
                    // Handle error
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                // Handle failure
                result.setValue(null);
            }
        });

        return result;
    }

    private Property mapPropertyResponseToProperty(PropertyResponse response) {
        if (response == null) {
            return null;
        }

        Property property = new Property(
                response.getProperty().getId(),
                response.getProperty().getDescription(),
                response.getProperty().getPropertyType(),
                response.getProperty().getPropertyName(),
                response.getProperty().getSharedType(),
                response.getProperty().getSharedName(),
                response.getProperty().getGuestNumber(),
                response.getProperty().getBedroomNumber(),
                response.getProperty().getBedsNumber(),
                response.getProperty().getBedroomLocked(),
                response.getProperty().getPrice().getNumberDecimal(),
                response.getProperty().getAddress1(),
                response.getProperty().getAddress2(),
                response.getProperty().getCity(),
                response.getProperty().getProvince(),
                response.getProperty().getCountry(),
                response.getProperty().getPostalCode(),
                response.getProperty().getLatitude(),
                response.getProperty().getLongitude()
        );

        return property;
    }
}
