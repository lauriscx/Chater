package com.gym.chater.Data;

public class User {
    public String name;
    public String email;
    public String phoneNumber;
    public String provider;
    public String profileImage;
    public String userID;

    public User() {
        this.name = "user";
        this.email = "email";
        this.phoneNumber = "phoneNumber";
        this.provider = "provider";
        this.profileImage = "https://elysator.com/wp-content/uploads/blank-profile-picture-973460_1280-e1523978675847.png";
        this.userID = "userID";
    }

    public User(String name, String email, String phoneNumber, String provider, String profileImage, String userID) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.provider = provider;
        this.profileImage = profileImage;
        this.userID = userID;
    }

    public void setName         (String name)           {
        this.name = name;
    }
    public void setEmail        (String email)          {
        this.email = email;
    }
    public void setPhoneNumber  (String phoneNumber)    {
        this.phoneNumber = phoneNumber;
    }
    public void setProvider     (String provider)       {
        this.provider = provider;
    }
    public void setProfileImage (String profileImage)  {
        this.profileImage = profileImage;
    }
    public void setUserID       (String userID)        {
        this.userID = userID;
    }

    public String getName           () {
        return name;
    }
    public String getEmail          () {
        return email;
    }
    public String getPhoneNumber    () {
        return phoneNumber;
    }
    public String getProvider       () {
        return provider;
    }
    public String getProfileImage   () {
        return profileImage;
    }
    public String getUserID() {
        return userID;
    }
}
