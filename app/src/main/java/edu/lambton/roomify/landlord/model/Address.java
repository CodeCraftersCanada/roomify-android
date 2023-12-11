package edu.lambton.roomify.landlord.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public record Address(@PrimaryKey @ColumnInfo(name = "address_id") Long id, String country,
                      String postalCode, String province, String city,
                      String subLocality, String featureName, String thoroughfare,
                      String subThoroughfare, double latitude, double longitude) {

    public Address(String country, String postalCode, String province, String city, String subLocality, String featureName, String thoroughfare, String subThoroughfare, double latitude, double longitude) {
        this(null, country, postalCode, province, city, subLocality, featureName, thoroughfare, subThoroughfare, latitude, longitude);
    }
}
