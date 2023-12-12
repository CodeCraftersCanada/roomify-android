package edu.lambton.roomify.student.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import edu.lambton.roomify.landlord.repository.PropertyRepository;
import edu.lambton.roomify.landlord.services.RoomifyApiClient;
import edu.lambton.roomify.student.dto.StudentPropertyResponse;
import edu.lambton.roomify.student.repository.StudentPropertyRepository;

public class ExplorePropertyViewModel extends ViewModel {

    private StudentPropertyRepository repository;

    public ExplorePropertyViewModel(@NonNull Application application) {
        this.repository = new StudentPropertyRepository(application, RoomifyApiClient.getApiService());

    }

    public LiveData<StudentPropertyResponse> getExploreAllProperties() {

        return this.repository.fetchAllStudentPropertiesExternal();
    }

}
