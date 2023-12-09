package edu.lambton.roomify.landlord.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.lambton.roomify.landlord.model.Property;
import edu.lambton.roomify.landlord.repository.PropertyRepository;

public class PropertyLandlordViewModel extends ViewModel {

    private final PropertyRepository repository;

    public PropertyLandlordViewModel(@NonNull Application application) {
        this.repository = new PropertyRepository(application);
    }

    public LiveData<List<Property>> getAllProperties() {
        return this.repository.getAllProperties();
    }

    public void saveProperty(@NonNull Property property) {
        repository.save(property);
    }
}
