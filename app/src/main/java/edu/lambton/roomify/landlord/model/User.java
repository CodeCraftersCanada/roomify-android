package edu.lambton.roomify.landlord.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    private long userUid;

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("uid")
    private String uid;

    @SerializedName("userType")
    private int userTypeId;

    @SerializedName("fullname")
    private String fullName;

    @SerializedName("email")
    private String email;
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


    public User(String id, String uid, int userTypeId, String fullName, String email, String phone, String college, String address, String imagePath, double latitude, double longitude) {
        this.id = id;
        this.uid = uid;
        this.userTypeId = userTypeId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.college = college;
        this.address = address;
        this.imagePath = imagePath;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", userTypeId=" + userTypeId +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", college='" + college + '\'' +
                ", address='" + address + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }


    public long getUserUid() {
        return userUid;
    }

    public void setUserUid(long userUid) {
        this.userUid = userUid;
    }
}
