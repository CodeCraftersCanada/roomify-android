package edu.lambton.roomify.chat.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import edu.lambton.roomify.landlord.viewmodel.PropertyLandlordViewModel;

public class ChatMessageViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public ChatMessageViewModelFactory(Application application) {
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChatMessageViewModel(application);
    }
}
