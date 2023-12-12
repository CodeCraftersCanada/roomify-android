package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class PropertyResponseInfo {
    @SerializedName("status")
    private boolean status;

    @SerializedName("property")
    private PropertyDTO property;

    public boolean isStatus() {
        return status;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public static class PropertyDTO {
        @SerializedName("_id")
        private String id;

        @SerializedName("user_id")
        private UserDTO userId;

        @SerializedName("property_status_id")
        private StatusDTO propertyStatusId;

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

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("updated_at")
        private Date updatedAt;

        @SerializedName("__v")
        private int version;

        @SerializedName("amenities")
        private List<AmenityDTO> amenities;

        @SerializedName("photos")
        private List<PhotoDTO> photos;

        public String getId() {
            return id;
        }

        public UserDTO getUserId() {
            return userId;
        }

        public StatusDTO getPropertyStatusId() {
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

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public int getVersion() {
            return version;
        }

        public List<AmenityDTO> getAmenities() {
            return amenities;
        }

        public List<PhotoDTO> getPhotos() {
            return photos;
        }
    }

    public static class UserDTO {
        @SerializedName("_id")
        private String id;

        @SerializedName("uid")
        private String uid;

        @SerializedName("email")
        private String email;

        @SerializedName("password")
        private String password;

        @SerializedName("user_type_id")
        private int userTypeId;

        @SerializedName("enabled")
        private int enabled;

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("updated_at")
        private Date updatedAt;

        @SerializedName("__v")
        private int version;

        @SerializedName("properties")
        private List<String> properties;

        public String getId() {
            return id;
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

        public int getEnabled() {
            return enabled;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public int getVersion() {
            return version;
        }

        public List<String> getProperties() {
            return properties;
        }
    }

    public static class StatusDTO {
        @SerializedName("_id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("__v")
        private int version;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public int getVersion() {
            return version;
        }
    }

    public static class PriceDTO {
        @SerializedName("$numberDecimal")
        private double numberDecimal;

        public double getNumberDecimal() {
            return numberDecimal;
        }
    }

    public static class AmenityDTO {
        @SerializedName("_id")
        private String id;

        @SerializedName("property_id")
        private String propertyId;

        @SerializedName("amenity_id")
        private int amenityId;

        @SerializedName("name")
        private String name;

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("__v")
        private int version;

        public String getId() {
            return id;
        }

        public String getPropertyId() {
            return propertyId;
        }

        public int getAmenityId() {
            return amenityId;
        }

        public String getName() {
            return name;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public int getVersion() {
            return version;
        }
    }

    public static class PhotoDTO {
        @SerializedName("_id")
        private String id;

        @SerializedName("property_id")
        private String propertyId;

        @SerializedName("photo_id")
        private int photoId;

        @SerializedName("path")
        private String path;

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("__v")
        private int version;

        public String getId() {
            return id;
        }

        public String getPropertyId() {
            return propertyId;
        }

        public int getPhotoId() {
            return photoId;
        }

        public String getPath() {
            return path;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public int getVersion() {
            return version;
        }
    }
}
