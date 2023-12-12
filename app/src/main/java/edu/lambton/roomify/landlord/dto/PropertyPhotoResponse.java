package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PropertyPhotoResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("propertyPhoto")
    private PropertyPhotoDetails propertyPhoto;

    @SerializedName("property")
    private PropertyDetails property;

    public static class PropertyPhotoDetails {

        @SerializedName("property_id")
        private String propertyId;

        @SerializedName("photo_id")
        private int photoId;

        @SerializedName("path")
        private String path;

        @SerializedName("_id")
        private String id;

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("__v")
        private int version;

        // Constructors, getters, and setters

        public PropertyPhotoDetails() {
        }

        public PropertyPhotoDetails(String propertyId, int photoId, String path, String id, Date createdAt, int version) {
            this.propertyId = propertyId;
            this.photoId = photoId;
            this.path = path;
            this.id = id;
            this.createdAt = createdAt;
            this.version = version;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "PropertyPhotoDetails{" +
                    "propertyId='" + propertyId + '\'' +
                    ", photoId=" + photoId +
                    ", path='" + path + '\'' +
                    ", id='" + id + '\'' +
                    ", createdAt=" + createdAt +
                    ", version=" + version +
                    '}';
        }
    }

    public static class PropertyDetails {

        @SerializedName("_id")
        private String id;

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

        @SerializedName("amenities")
        private List<String> amenities;

        @SerializedName("price")
        private BigDecimal price;
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
        @SerializedName("postalCode")
        private String postalCode;
        @SerializedName("latitude")
        private double latitude;
        @SerializedName("created_at")
        private Date createdAt;
        @SerializedName("updated_at")
        private Date updatedAt;
        @SerializedName("version")
        private int version;

        @SerializedName("photos")
        private List<String> photos;


        public PropertyDetails() {
        }

        public PropertyDetails(String id, String userId, int propertyStatusId, String name, String description, int propertyType, String propertyName, int sharedType, String sharedName, int guestNumber, int bedroomNumber, int bedsNumber, int bedroomLocked, BigDecimal price, String address1, String address2, String city, String province, String country, String postalCode, double latitude, Date createdAt, Date updatedAt, int version, List<String> amenities, List<String> photos) {
            this.id = id;
            this.userId = userId;
            this.propertyStatusId = propertyStatusId;
            this.name = name;
            this.description = description;
            this.propertyType = propertyType;
            this.propertyName = propertyName;
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
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.version = version;
            this.amenities = amenities;
            this.photos = photos;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getPropertyStatusId() {
            return propertyStatusId;
        }

        public void setPropertyStatusId(int propertyStatusId) {
            this.propertyStatusId = propertyStatusId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(int propertyType) {
            this.propertyType = propertyType;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public int getSharedType() {
            return sharedType;
        }

        public void setSharedType(int sharedType) {
            this.sharedType = sharedType;
        }

        public String getSharedName() {
            return sharedName;
        }

        public void setSharedName(String sharedName) {
            this.sharedName = sharedName;
        }

        public int getGuestNumber() {
            return guestNumber;
        }

        public void setGuestNumber(int guestNumber) {
            this.guestNumber = guestNumber;
        }

        public int getBedroomNumber() {
            return bedroomNumber;
        }

        public void setBedroomNumber(int bedroomNumber) {
            this.bedroomNumber = bedroomNumber;
        }

        public int getBedsNumber() {
            return bedsNumber;
        }

        public void setBedsNumber(int bedsNumber) {
            this.bedsNumber = bedsNumber;
        }

        public int getBedroomLocked() {
            return bedroomLocked;
        }

        public void setBedroomLocked(int bedroomLocked) {
            this.bedroomLocked = bedroomLocked;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<String> getAmenities() {
            return amenities;
        }

        public void setAmenities(List<String> amenities) {
            this.amenities = amenities;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }

        @Override
        public String toString() {
            return "PropertyDetails{" +
                    "id='" + id + '\'' +
                    ", userId='" + userId + '\'' +
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
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", version=" + version +
                    ", amenities=" + amenities +
                    ", photos=" + photos +
                    '}';
        }
    }
}

