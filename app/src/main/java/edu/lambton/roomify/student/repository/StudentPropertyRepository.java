package edu.lambton.roomify.student.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

import edu.lambton.roomify.common.AppDatabase;
import edu.lambton.roomify.landlord.services.ApiService;
import edu.lambton.roomify.student.dto.StudentPropertyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPropertyRepository {

    private final ApiService apiService;
    private final FirebaseAuth firebaseAuth;


    public StudentPropertyRepository(Application application, ApiService apiService) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.apiService = apiService;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public LiveData<StudentPropertyResponse> fetchAllStudentPropertiesExternal() {
        MutableLiveData<StudentPropertyResponse> result = new MutableLiveData<>();
        apiService.getAllProperties().enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<StudentPropertyResponse> call, Response<StudentPropertyResponse> response) {
                if (response.isSuccessful()) {
                    StudentPropertyResponse studentPropertyResponse = response.body();

                    System.out.println("STUDENT PROPERTY RESPONSE: " + studentPropertyResponse);

                    result.setValue(studentPropertyResponse);
                }
            }

            @Override
            public void onFailure(Call<StudentPropertyResponse> call, Throwable t) {

            }
        });

        return result;
    }
}
