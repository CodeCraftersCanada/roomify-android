package edu.lambton.roomify.landlord.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public record Property(@PrimaryKey @ColumnInfo(name = "PROPERTY_ID") Long propertyId,
                       String description, @ColumnInfo(name = "PROPERTY_NAME") int propertyType,
                       @ColumnInfo(name = "PROPERTY_NAME") String propertyName,
                       @ColumnInfo(name = "SHARED_TYPE") int sharedType,
                       @ColumnInfo(name = "SHARED_NAME") String sharedName,
                       @ColumnInfo(name = "GUEST_NUMBER") int guestNumber,
                       @ColumnInfo(name = "BEDROOM_NUMBER") int bedroomNumber,
                       @ColumnInfo(name = "BEDS_NUMBER") int bedsNumber,
                       @ColumnInfo(name = "BEDROOM_LOCKED") //boolean
                       boolean bedroomLocked, double price, String address1, String address2,
                       String city, String province, String country, String postal_code,
                       double latitude, double longitude
) {

}
