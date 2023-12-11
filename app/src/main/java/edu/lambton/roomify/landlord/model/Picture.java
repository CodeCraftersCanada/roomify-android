package edu.lambton.roomify.landlord.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public record Picture(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "PICTURE_ID")
        Long id,
        @ColumnInfo(name = "PICTURE_PATH")
        String path,
        @ColumnInfo(name = "CREATION_DATE")
        Long creationDate,
        @ColumnInfo(name = "PARENT_PROPERTY_ID")
        long parentPropertyId
) implements Serializable {

    public Picture(
            String path,
            Long creationDate,
            long parentPropertyId) {

        this(null, path, creationDate, parentPropertyId);
    }
}
