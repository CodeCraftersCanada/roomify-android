package edu.lambton.roomify.landlord.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProperetyLandlordViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public ProperetyLandlordViewModelFactory(Application application) {
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PropertyLandlordViewModel(application);
    }
}
