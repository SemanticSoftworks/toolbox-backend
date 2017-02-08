package com.example.model;

import java.util.Calendar;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */
public class AdDTO {
    private int adId;
    private UserDTO user;
    private CategoryDTO category;
    private String title;
    private String description;
    private Calendar duration;
    private byte[] image;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDuration() {
        return duration;
    }

    public void setDuration(Calendar duration) {
        this.duration = duration;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
