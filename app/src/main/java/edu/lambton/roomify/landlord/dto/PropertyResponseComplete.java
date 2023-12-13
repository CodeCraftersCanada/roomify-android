package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertyResponseComplete {

    @Expose
    @SerializedName("status")
    private boolean status;

    @Expose
    @SerializedName("properties")
    private List<Property> properties = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public static class Property {
        private String _id;
        @SerializedName("user_id")
        private UserId userId;
        @SerializedName("property_status_id")
        private PropertyStatusId property_status_id;
        private String name;
        @SerializedName("description")
        private String description;
        private int property_type;
        private int shared_type;
        private String shared_name;
        private int guest_number;
        @SerializedName("bedroom_number")
        private int bedroom_number;
        @SerializedName("beds_number")
        private int beds_number;
        private int bedroom_locked;
        private Price price;
        private String address1;
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
        private List<String> amenities;
        @SerializedName("photos")
        private List<Photo> photos;
        @SerializedName("bookings")
        private List<String> bookings;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public UserId getUser_id() {
            return userId;
        }

        public void setUser_id(UserId user_Id_id) {
            this.userId = user_Id_id;
        }

        public PropertyStatusId getProperty_status_id() {
            return property_status_id;
        }

        public void setProperty_status_id(PropertyStatusId property_status_id) {
            this.property_status_id = property_status_id;
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

        public int getProperty_type() {
            return property_type;
        }

        public void setProperty_type(int property_type) {
            this.property_type = property_type;
        }

        public int getShared_type() {
            return shared_type;
        }

        public void setShared_type(int shared_type) {
            this.shared_type = shared_type;
        }

        public String getShared_name() {
            return shared_name;
        }

        public void setShared_name(String shared_name) {
            this.shared_name = shared_name;
        }

        public int getGuest_number() {
            return guest_number;
        }

        public void setGuest_number(int guest_number) {
            this.guest_number = guest_number;
        }

        public int getBedroom_number() {
            return bedroom_number;
        }

        public void setBedroom_number(int bedroom_number) {
            this.bedroom_number = bedroom_number;
        }

        public int getBeds_number() {
            return beds_number;
        }

        public void setBeds_number(int beds_number) {
            this.beds_number = beds_number;
        }

        public int getBedroom_locked() {
            return bedroom_locked;
        }

        public void setBedroom_locked(int bedroom_locked) {
            this.bedroom_locked = bedroom_locked;
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

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public List<String> getAmenities() {
            return amenities;
        }

        public void setAmenities(List<String> amenities) {
            this.amenities = amenities;
        }

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public List<String> getBookings() {
            return bookings;
        }

        public void setBookings(List<String> bookings) {
            this.bookings = bookings;
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

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public static class UserId {
            private String _id;
            private String uid;
            private String email;
            @SerializedName("user_type_id")
            private int userTypeId;
            @SerializedName("fullname")
            private String fullName;
            @SerializedName("phone")
            private String phone;
            @SerializedName("enabled")
            private int enabled;
            @SerializedName("college")
            private String college;
            @SerializedName("image_path")
            String imagePath;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
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

            public int getUserTypeId() {
                return userTypeId;
            }

            public void setUserTypeId(int userTypeId) {
                this.userTypeId = userTypeId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getEnabled() {
                return enabled;
            }

            public void setEnabled(int enabled) {
                this.enabled = enabled;
            }

            public String getCollege() {
                return college;
            }

            public void setCollege(String college) {
                this.college = college;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }
        }

        public static class PropertyStatusId {
            private int _id;
            @SerializedName("name")
            private String propertyStatus;
            private String createdAt;
            private int __v;

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
            }

            public String getPropertyStatus() {
                return propertyStatus;
            }

            public void setPropertyStatus(String propertyStatus) {
                this.propertyStatus = propertyStatus;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }

        public static class Price {
            @SerializedName("$numberDecimal")
            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class Photo {
            private String _id;
            private String property_id;
            private int photo_id;
            private String path;
            private String createdAt;
            private int __v;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getProperty_id() {
                return property_id;
            }

            public void setProperty_id(String property_id) {
                this.property_id = property_id;
            }

            public int getPhoto_id() {
                return photo_id;
            }

            public void setPhoto_id(int photo_id) {
                this.photo_id = photo_id;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }
    }

}
