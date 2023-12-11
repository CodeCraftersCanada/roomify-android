package edu.lambton.roomify.landlord.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public record PropertyPhotos(
        @Embedded
        Property property,

        @Relation(parentColumn = "PROPERTY_ID", entityColumn = "PARENT_PROPERTY_ID")
        List<Picture> pictures
) {
}
