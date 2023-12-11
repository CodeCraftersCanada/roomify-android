package edu.lambton.roomify.landlord.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import edu.lambton.roomify.landlord.model.User;
import edu.lambton.roomify.landlord.repository.UserRepository;
import edu.lambton.roomify.landlord.services.ApiService;
import edu.lambton.roomify.landlord.services.RoomifyApiClient;

public class UserLandlordViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final ApiService apiService;

    public UserLandlordViewModel(Application application) {
        apiService = RoomifyApiClient.getApiService();
        this.userRepository = new UserRepository(application, apiService);
    }

    public void createUser(User user) {
        this.userRepository.saveExternal(user);
    }

    public void updateUser(User user) {
        this.userRepository.updateExternal(user);
    }

    public LiveData<User> loadProfileInfo(String uid) {
        return this.userRepository.getUserByIdExternal(uid);
    }
}
