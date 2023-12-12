package edu.lambton.roomify.landlord.model;

import android.graphics.drawable.Drawable;


public record PlaceRowOption(int propertyType, Drawable drawable, String text,
                             boolean isSelected) {

}
