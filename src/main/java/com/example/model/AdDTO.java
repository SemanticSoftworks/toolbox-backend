package com.example.model;

import java.util.Calendar;

/**
 * Created by Teddy on 2017-02-08.
 */
public class AdDTO {

    private long adId;
    private long userId;
    private String category;
    private String title2;
    private String description;
    private Calendar duration;

    public AdDTO(long adId, long user_id, String category, String title, String description, Calendar duration) {
        this.adId = adId;
        this.userId = user_id;
        this.category = category;
        this.title2 = title;
        this.description = description;
        this.duration = duration;
    }

    public long getAdId() {
        return adId;
    }

    public long getUser() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title2;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDuration() {
        return duration;
    }
}
