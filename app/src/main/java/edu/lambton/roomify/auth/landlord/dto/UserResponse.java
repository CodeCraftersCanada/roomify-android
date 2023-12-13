package edu.lambton.roomify.auth.landlord.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("user")
    private UserDTO user;

    public UserResponse(boolean status, UserDTO user) {
        this.status = status;
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public UserDTO getUser() {
        return user;
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
        private UserTypeId userType;

        @SerializedName("fullname")
        private String fullname;

        @SerializedName("phone")
        private String phone;

        @SerializedName("enabled")
        private int enabled;

        @SerializedName("created_at")
        private Date createdAt;

        @SerializedName("updated_at")
        private Date updatedAt;

        @SerializedName("__v")
        private int version;

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

        public UserTypeId getUserType() {
            return userType;
        }

        public String getFullname() {
            return fullname;
        }

        public String getPhone() {
            return phone;
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
    }

    public static class UserTypeId {
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
}
