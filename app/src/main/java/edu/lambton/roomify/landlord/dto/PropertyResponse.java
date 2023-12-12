package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.SerializedName;

public class PropertyResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("property")
    private PropertyDTO property;

    @SerializedName("user")
    private UserDTO user;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public UserDTO getUser() {
        return user;
    }

    public static class PropertyDTO {


        @SerializedName("user_id")
        private String userId;

        @SerializedName("property_status_id")
        private int propertyStatusId;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        @SerializedName("property_type")
        private int propertyType;

        @SerializedName("property_name")
        private String propertyName;

        @SerializedName("shared_type")
        private int sharedType;

        @SerializedName("shared_name")
        private String sharedName;

        @SerializedName("guest_number")
        private int guestNumber;

        @SerializedName("bedroom_number")
        private int bedroomNumber;

        @SerializedName("beds_number")
        private int bedsNumber;

        @SerializedName("bedroom_locked")
        private int bedroomLocked;

        @SerializedName("price")
        private PriceDTO price;

        @SerializedName("address1")
        private String address1;

        @SerializedName("address2")
        private String address2;

        @SerializedName("city")
        private String city;

        @SerializedName("province")
        private String province;

        @SerializedName("country")
        private String country;

        @SerializedName("postal_code")
        private String postalCode;

        @SerializedName("latitude")
        private double latitude;

        @SerializedName("longitude")
        private double longitude;

        @SerializedName("amenities")
        private String[] amenities;

        @SerializedName("photos")
        private String[] photos;

        @SerializedName("_id")
        private String id;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("__v")
        private int version;

        public String getUserId() {
            return userId;
        }

        public int getPropertyStatusId() {
            return propertyStatusId;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getPropertyType() {
            return propertyType;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public int getSharedType() {
            return sharedType;
        }

        public String getSharedName() {
            return sharedName;
        }

        public int getGuestNumber() {
            return guestNumber;
        }

        public int getBedroomNumber() {
            return bedroomNumber;
        }

        public int getBedsNumber() {
            return bedsNumber;
        }

        public int getBedroomLocked() {
            return bedroomLocked;
        }

        public PriceDTO getPrice() {
            return price;
        }

        public String getAddress1() {
            return address1;
        }

        public String getAddress2() {
            return address2;
        }

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getCountry() {
            return country;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String[] getAmenities() {
            return amenities;
        }

        public String[] getPhotos() {
            return photos;
        }

        public String getId() {
            return id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

    }

    public static class PriceDTO {
        @SerializedName("$numberDecimal")
        private double numberDecimal;

        public double getNumberDecimal() {
            return numberDecimal;
        }
    }

    public static class UserDTO {

    }
}
