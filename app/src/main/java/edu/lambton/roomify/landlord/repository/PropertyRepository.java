package edu.lambton.roomify.landlord.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.lambton.roomify.common.AppDatabase;
import edu.lambton.roomify.landlord.dao.PropertyDao;
import edu.lambton.roomify.landlord.model.Property;

public class PropertyRepository {
    private final PropertyDao propertyDao;

    public PropertyRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        propertyDao = db.propertyDao();
    }


    public LiveData<List<Property>> getAllProperties() {
        return this.propertyDao.getAllProperties();
    }

    public void save(Property property) {
        AppDatabase.databaseWriterExecutor.execute(() -> propertyDao.saveProperty(property));
    }
}
