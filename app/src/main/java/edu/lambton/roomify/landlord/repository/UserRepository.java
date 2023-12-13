package edu.lambton.roomify.landlord.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.google.firebase.auth.FirebaseAuth;

import edu.lambton.roomify.common.AppDatabase;
import edu.lambton.roomify.common.UserMapper;
import edu.lambton.roomify.landlord.dao.UserDao;
import edu.lambton.roomify.landlord.dto.UserResponse;
import edu.lambton.roomify.landlord.model.User;
import edu.lambton.roomify.landlord.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private final UserDao userDao;
    private final ApiService apiService;
    private final FirebaseAuth firebaseAuth;


    public UserRepository(Application application, ApiService apiService) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.apiService = apiService;
        firebaseAuth = FirebaseAuth.getInstance();
        userDao = db.userDao();
    }

    public void save(User user) {
        AppDatabase.databaseWriterExecutor.execute(() -> userDao.saveUser(user));
    }

    // Save user to the server and update local database
    public void saveExternal(User user) {
        apiService.saveUser(user).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User savedUser = response.body();
                    // Save the updated user into the local database
                    System.out.println("UserId saved into external service: " + savedUser);
                    save(savedUser);
                } else {
                    System.err.println("Something happened " + response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.err.println(t.getMessage());
            }
        });
    }

    public LiveData<User> getUserById(String uid) {
        return userDao.searchUserById(uid);
    }

    public LiveData<User> getUserByIdExternal(String uid) {
        MediatorLiveData<User> resultLiveData = new MediatorLiveData<>();

        LiveData<User> localUserLiveData = userDao.searchUserById(uid);
        resultLiveData.addSource(localUserLiveData, user -> {
            if (user != null) {
                // Local data is available, update the result
                resultLiveData.setValue(user);
            } else {
                // Local data is not available, fetch from the remote service
                apiService.getUserById(uid).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            UserResponse serverUser = response.body();

                            assert serverUser != null;
                            User userConverted = UserMapper.mapUserResponseToUser(serverUser);
                            // Save the updated user into the local database
                            AppDatabase.databaseWriterExecutor.execute(() -> userDao.updateUser(userConverted));
                            // Update the result with the server data
                            resultLiveData.setValue(userConverted);
                        } else {
                            // Handle error
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }


                });
            }
        });

        return resultLiveData;
    }

    public void update(User user) {
        AppDatabase.databaseWriterExecutor.execute(() -> userDao.updateUser(user));
    }


    // Update user on the server and update local database
    public void updateExternal(User user) {
        // Assuming ApiService has a method for updating a user
        apiService.updateUser(user).enqueue(new Callback<edu.lambton.roomify.landlord.dto.UserResponse>() {
            @Override
            public void onResponse(Call<edu.lambton.roomify.landlord.dto.UserResponse> call, Response<edu.lambton.roomify.landlord.dto.UserResponse> response) {
                if (response.isSuccessful()) {
                    edu.lambton.roomify.landlord.dto.UserResponse updatedUser = response.body();
                    // Update the user in the local database

                    assert updatedUser != null;
                    User userMapped = userResponseMapper(updatedUser);
                    update(userMapped);
                    System.out.println("UserId updated: " + updatedUser);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<edu.lambton.roomify.landlord.dto.UserResponse> call, Throwable t) {

            }
        });
    }

    public User userResponseMapper(@NonNull edu.lambton.roomify.landlord.dto.UserResponse userResponse) {
        return new User(
                userResponse.getUser().getId(),
                userResponse.getUser().getUid(),
                userResponse.getUser().getUserType().getId(),
                userResponse.getUser().getFullName(),
                userResponse.getUser().getEmail(),
                userResponse.getUser().getPhone(),
                userResponse.getUser().getCollege(),
                userResponse.getUser().getAddress(),
                userResponse.getUser().getImagePath(),
                userResponse.getUser().getLatitude(),
                userResponse.getUser().getLatitude()
        );
    }

}

