package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.SerializedName;

import org.bson.types.ObjectId;


public class PropertyRequest {

    @SerializedName("user_id")
    private final String userId;

    @SerializedName("property_status_id")
    private final int propertyStatusId;

    @SerializedName("name")
    private final String name;

    @SerializedName("description")
    private final String description;

    @SerializedName("property_type")
    private final int propertyType;

    @SerializedName("shared_type")
    private final int sharedType;

    @SerializedName("shared_name")
    private final String sharedName;

    @SerializedName("guest_number")
    private final int guestNumber;

    @SerializedName("bedroom_number")
    private final int bedroomNumber;

    @SerializedName("beds_number")
    private final int bedsNumber;

    @SerializedName("bedroom_locked")
    private final int bedroomLocked;

    @SerializedName("price")
    private final double price;

    @SerializedName("address1")
    private final String address1;

    @SerializedName("address2")
    private final String address2;

    @SerializedName("city")
    private final String city;

    @SerializedName("province")
    private final String province;

    @SerializedName("country")
    private final String country;

    @SerializedName("postal_code")
    private final String postalCode;

    @SerializedName("latitude")
    private final double latitude;

    @SerializedName("longitude")
    private final double longitude;

    public PropertyRequest(String userId, int propertyStatusId, String name, String description, int propertyType, int sharedType, String sharedName,
                           int guestNumber, int bedroomNumber, int bedsNumber, int bedroomLocked, double price,
                           String address1, String address2, String city, String province, String country,
                           String postalCode, double latitude, double longitude) {

        this.userId = userId;
        this.propertyStatusId = propertyStatusId;
        this.name = name;
        this.description = description;
        this.propertyType = propertyType;
        this.sharedType = sharedType;
        this.sharedName = sharedName;
        this.guestNumber = guestNumber;
        this.bedroomNumber = bedroomNumber;
        this.bedsNumber = bedsNumber;
        this.bedroomLocked = bedroomLocked;
        this.price = price;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUserId() {
        return userId;
    }
}
