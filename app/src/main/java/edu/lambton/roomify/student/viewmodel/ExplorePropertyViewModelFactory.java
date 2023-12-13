package edu.lambton.roomify.student.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class ExplorePropertyViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ExplorePropertyViewModelFactory(Application application) {
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExplorePropertyViewModel(application);
    }
}
