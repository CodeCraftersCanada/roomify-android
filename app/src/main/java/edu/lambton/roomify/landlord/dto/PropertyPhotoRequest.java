package edu.lambton.roomify.landlord.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PropertyPhotoRequest {

    @SerializedName("property_id")
    private String propertyId;
    @SerializedName("photo_id")
    private int photoId;
    @SerializedName("path")
    private String path;

    public PropertyPhotoRequest() {
    }

    public PropertyPhotoRequest(String propertyId, int photoId, String path) {
        this.propertyId = propertyId;
        this.photoId = photoId;
        this.path = path;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @NonNull
    @Override
    public String toString() {
        return "PropertyPhotoRequest{" +
                "propertyId='" + propertyId + '\'' +
                ", photoId=" + photoId +
                ", path='" + path + '\'' +
                '}';
    }
}

