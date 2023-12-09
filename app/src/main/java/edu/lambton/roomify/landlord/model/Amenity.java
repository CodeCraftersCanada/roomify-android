package edu.lambton.roomify.landlord.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public record Amenity(@PrimaryKey long amenityId,
                      @ColumnInfo(name = "PARENT_PROPERTY_ID") long parentPropertyId,
                      String name
) {

}
