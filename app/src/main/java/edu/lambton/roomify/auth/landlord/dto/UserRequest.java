package edu.lambton.roomify.auth.landlord.dto;

import com.google.gson.annotations.SerializedName;

public class UserRequest {

    @SerializedName("uid")
    private String uid;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("user_type_id")
    private int userTypeId;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("phone")
    private String phone;

    @SerializedName("college")
    private String college;

    @SerializedName("address")
    private String address;

    @SerializedName("image_path")
    private String imagePath;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    // Constructors, getters, and setters

    public UserRequest(String uid, String email, String password, int userTypeId, String fullname, String phone,
                       String college, String address, String imagePath, double latitude, double longitude) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.userTypeId = userTypeId;
        this.fullname = fullname;
        this.phone = phone;
        this.college = college;
        this.address = address;
        this.imagePath = imagePath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getCollege() {
        return college;
    }

    public String getAddress() {
        return address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
