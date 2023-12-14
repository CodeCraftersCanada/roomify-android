package edu.lambton.roomify.chat.dto;

public class Contact {

    private String userId;
    private String profilePictureUrl;
    private String displayName;


    public Contact() {
    }

    public Contact(String displayName, String profilePictureUrl, String userId) {
        this.displayName = displayName;
        this.profilePictureUrl = profilePictureUrl;
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }



    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
