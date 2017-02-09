package com.example.model;

import com.example.domain.Category;

import java.util.Calendar;

/**
 * Created by Teddy on 2017-02-08.
 */
public class AdDTO {

    private long adId;
    private long userId;
    private CategoryDTO category; // fel --> domain objekt
    private String title;
    private String description;
    private Calendar duration;

    public AdDTO(long adId, long user_id, CategoryDTO category, String title, String description, Calendar duration) {
        this.adId = adId;
        this.userId = user_id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public long getAdId() {
        return adId;
    }

    public long getUser() {
        return userId;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDuration() {
        return duration;
    }
}
