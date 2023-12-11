package edu.lambton.roomify.landlord.dto;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User user;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public static class User {

        @SerializedName("_id")
        private String id;

        @SerializedName("uid")
        private String uid;

        @SerializedName("email")
        private String email;

        @SerializedName("password")
        private String password;

        @SerializedName("user_type_id")
        private UserType userType;

        @SerializedName("fullname")
        private String fullName;

        @SerializedName("phone")
        private String phone;

        @SerializedName("image_path")
        private String imagePath;

        @SerializedName("college")
        private String college;

        @SerializedName("address")
        private String address;

        @SerializedName("latitude")
        private double latitude;

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

        public String getPassword() {
            return password;
        }

        public UserType getUserType() {
            return userType;
        }

        public String getFullName() {
            return fullName;
        }

        public String getPhone() {
            return phone;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getCollege() {
            return college;
        }

        public String getAddress() {
            return address;
        }

        public double getLatitude() {
            return latitude;
        }

        // Add getters for other fields

        public static class UserType {

            @SerializedName("_id")
            private int id;

            @SerializedName("name")
            private String name;

            @SerializedName("created_at")
            private String createdAt;

            // Add other fields as needed

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            // Add getters for other fields
        }
    }
}
