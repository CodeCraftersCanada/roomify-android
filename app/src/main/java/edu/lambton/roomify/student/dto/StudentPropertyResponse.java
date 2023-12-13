package edu.lambton.roomify.student.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentPropertyResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("properties")
    private List<PropertyDTO> properties;

    public boolean isStatus() {
        return status;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public static class PropertyDTO {

        @SerializedName("_id")
        private String id;

        @SerializedName("user_id")
        private UserDTO userId;

        @SerializedName("property_status_id")
        private PropertyStatusDTO propertyStatusId;

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
        private List<AmenityDTO> amenities;

        @SerializedName("photos")
        private List<PhotoDTO> photos;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        @SerializedName("__v")
        private int version;

        public String getId() {
            return id;
        }

        public UserDTO getUserId() {
            return userId;
        }

        public PropertyStatusDTO getPropertyStatusId() {
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

        public List<AmenityDTO> getAmenities() {
            return amenities;
        }

        public List<PhotoDTO> getPhotos() {
            return photos;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public int getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "PropertyDTO{" +
                    "id='" + id + '\'' +
                    ", userId=" + userId +
                    ", propertyStatusId=" + propertyStatusId +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", propertyType=" + propertyType +
                    ", propertyName='" + propertyName + '\'' +
                    ", sharedType=" + sharedType +
                    ", sharedName='" + sharedName + '\'' +
                    ", guestNumber=" + guestNumber +
                    ", bedroomNumber=" + bedroomNumber +
                    ", bedsNumber=" + bedsNumber +
                    ", bedroomLocked=" + bedroomLocked +
                    ", price=" + price +
                    ", address1='" + address1 + '\'' +
                    ", address2='" + address2 + '\'' +
                    ", city='" + city + '\'' +
                    ", province='" + province + '\'' +
                    ", country='" + country + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", amenities=" + amenities +
                    ", photos=" + photos +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", version=" + version +
                    '}';
        }
    }

    public static class PriceDTO {
        @SerializedName("$numberDecimal")
        private double numberDecimal;

        public double getNumberDecimal() {
            return numberDecimal;
        }

        @Override
        public String toString() {
            return "PriceDTO{" +
                    "numberDecimal=" + numberDecimal +
                    '}';
        }
    }

    public static class UserDTO {
        @SerializedName("_id")
        private String id;

        @SerializedName("uid")
        private String uid;

        @SerializedName("email")
        private String email;

        // Add other fields as needed

        public String getId() {
            return id;
        }

        public String getUid() {
            return uid;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "UserDTO{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static class PropertyStatusDTO {
        @SerializedName("_id")
        private int id;

        @SerializedName("name")
        private String name;

        // Add other fields as needed

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "PropertyStatusDTO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
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
        private String createdAt;

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

        public String getCreatedAt() {
            return createdAt;
        }

        public int getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "AmenityDTO{" +
                    "id='" + id + '\'' +
                    ", propertyId='" + propertyId + '\'' +
                    ", amenityId=" + amenityId +
                    ", name='" + name + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", version=" + version +
                    '}';
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
        private String createdAt;

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

        public String getCreatedAt() {
            return createdAt;
        }

        public int getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "PhotoDTO{" +
                    "id='" + id + '\'' +
                    ", propertyId='" + propertyId + '\'' +
                    ", photoId=" + photoId +
                    ", path='" + path + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", version=" + version +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StudentPropertyResponse{" +
                "status=" + status +
                ", properties=" + properties +
                '}';
    }
}
