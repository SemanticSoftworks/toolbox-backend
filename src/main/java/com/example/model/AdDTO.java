package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public AdDTO(@JsonProperty("adId")long adId,
                 @JsonProperty("user_id")long user_id,
                 @JsonProperty("category")String category,
                 @JsonProperty("title")String title,
                 @JsonProperty("description")String description,
                 @JsonProperty("duration")Calendar duration) {
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
