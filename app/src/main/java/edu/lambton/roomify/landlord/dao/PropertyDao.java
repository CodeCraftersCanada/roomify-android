package edu.lambton.roomify.landlord.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.lambton.roomify.landlord.model.Property;

@Dao
public abstract class PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void saveProperty(Property property);

    @Query("SELECT * FROM PROPERTY WHERE GUEST_NUMBER = :landlordId")
    public abstract LiveData<Property> getPropertyByLandlordId(String landlordId);

    @Query("SELECT * FROM Property")
    public abstract LiveData<List<Property>> getAllProperties();

    @Query("SELECT * FROM Property WHERE USER_ID = :userId")
    public abstract LiveData<List<Property>> getAllPropertiesCreatedByMe(String userId);

}
