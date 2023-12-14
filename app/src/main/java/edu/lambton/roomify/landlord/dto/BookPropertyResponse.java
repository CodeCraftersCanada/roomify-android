package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookPropertyResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("booking")
    @Expose
    private Booking booking;

    @SerializedName("property")
    @Expose
    private Property property;

    @SerializedName("user")
    @Expose
    private User user;

    public static class Booking {

        @SerializedName("property_id")
        @Expose
        private String propertyId;

        @SerializedName("booked_by")
        @Expose
        private String bookedBy;

        @SerializedName("amount")
        @Expose
        private Amount amount;

        @SerializedName("start_date")
        @Expose
        private String startDate;

        @SerializedName("end_date")
        @Expose
        private String endDate;

        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("created_at")
        @Expose
        private String createdAt;

        @SerializedName("__v")
        @Expose
        private Integer v;

        public String getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(String propertyId) {
            this.propertyId = propertyId;
        }

        public String getBookedBy() {
            return bookedBy;
        }

        public void setBookedBy(String bookedBy) {
            this.bookedBy = bookedBy;
        }

        public Amount getAmount() {
            return amount;
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }
    }


    public static class Amount {

        @SerializedName("$numberDecimal")
        @Expose
        private String numberDecimal;

        public String getNumberDecimal() {
            return numberDecimal;
        }

        public void setNumberDecimal(String numberDecimal) {
            this.numberDecimal = numberDecimal;
        }
    }

    public class Property {

        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("user_id")
        @Expose
        private String userId;

        @SerializedName("property_status_id")
        @Expose
        private Integer propertyStatusId;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("property_type")
        @Expose
        private Integer propertyType;

        @SerializedName("property_name")
        @Expose
        private String propertyName;

        @SerializedName("shared_type")
        @Expose
        private Integer sharedType;

        @SerializedName("shared_name")
        @Expose
        private String sharedName;

        @SerializedName("guest_number")
        @Expose
        private Integer guestNumber;

        @SerializedName("bedroom_number")
        @Expose
        private Integer bedroomNumber;

        @SerializedName("beds_number")
        @Expose
        private Integer bedsNumber;

        @SerializedName("bedroom_locked")
        @Expose
        private Integer bedroomLocked;

        @SerializedName("price")
        @Expose
        private Price price;

        @SerializedName("address1")
        @Expose
        private String address1;

        @SerializedName("address2")
        @Expose
        private String address2;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("province")
        @Expose
        private String province;

        @SerializedName("country")
        @Expose
        private String country;

        @SerializedName("postal_code")
        @Expose
        private String postalCode;

        @SerializedName("latitude")
        @Expose
        private Double latitude;

        @SerializedName("created_at")
        @Expose
        private String createdAt;

        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        @SerializedName("__v")
        @Expose
        private Integer v;

        @SerializedName("amenities")
        @Expose
        private List<String> amenities = null;

        @SerializedName("photos")
        @Expose
        private List<String> photos = null;

        @SerializedName("longitude")
        @Expose
        private Double longitude;

        @SerializedName("bookings")
        @Expose
        private List<String> bookings = null;

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

        public Integer getPropertyStatusId() {
            return propertyStatusId;
        }

        public void setPropertyStatusId(Integer propertyStatusId) {
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

        public Integer getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(Integer propertyType) {
            this.propertyType = propertyType;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public Integer getSharedType() {
            return sharedType;
        }

        public void setSharedType(Integer sharedType) {
            this.sharedType = sharedType;
        }

        public String getSharedName() {
            return sharedName;
        }

        public void setSharedName(String sharedName) {
            this.sharedName = sharedName;
        }

        public Integer getGuestNumber() {
            return guestNumber;
        }

        public void setGuestNumber(Integer guestNumber) {
            this.guestNumber = guestNumber;
        }

        public Integer getBedroomNumber() {
            return bedroomNumber;
        }

        public void setBedroomNumber(Integer bedroomNumber) {
            this.bedroomNumber = bedroomNumber;
        }

        public Integer getBedsNumber() {
            return bedsNumber;
        }

        public void setBedsNumber(Integer bedsNumber) {
            this.bedsNumber = bedsNumber;
        }

        public Integer getBedroomLocked() {
            return bedroomLocked;
        }

        public void setBedroomLocked(Integer bedroomLocked) {
            this.bedroomLocked = bedroomLocked;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
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

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
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

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public List<String> getBookings() {
            return bookings;
        }

        public void setBookings(List<String> bookings) {
            this.bookings = bookings;
        }
    }

    public static class Price {

        @SerializedName("$numberDecimal")
        @Expose
        private String numberDecimal;


        public String getNumberDecimal() {
            return numberDecimal;
        }

        public void setNumberDecimal(String numberDecimal) {
            this.numberDecimal = numberDecimal;
        }

    }

    public static class User {

        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("uid")
        @Expose
        private String uid;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("password")
        @Expose
        private String password;

        @SerializedName("user_type_id")
        @Expose
        private Integer userTypeId;

        @SerializedName("enabled")
        @Expose
        private Integer enabled;

        @SerializedName("created_at")
        @Expose
        private String createdAt;

        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        @SerializedName("__v")
        @Expose
        private Integer v;

        @SerializedName("properties")
        @Expose
        private List<String> properties = null;

        @SerializedName("bookings")
        @Expose
        private List<String> bookings = null;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getUserTypeId() {
            return userTypeId;
        }

        public void setUserTypeId(Integer userTypeId) {
            this.userTypeId = userTypeId;
        }

        public Integer getEnabled() {
            return enabled;
        }

        public void setEnabled(Integer enabled) {
            this.enabled = enabled;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public List<String> getProperties() {
            return properties;
        }

        public void setProperties(List<String> properties) {
            this.properties = properties;
        }

        public List<String> getBookings() {
            return bookings;
        }

        public void setBookings(List<String> bookings) {
            this.bookings = bookings;
        }
    }

}
