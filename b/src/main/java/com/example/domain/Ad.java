package com.example.domain;

import com.example.model.AdDTO;
import com.example.model.UserDTO;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by dani on 2017-02-06.
 */

@Entity(name = "Ad")
@Table(name = "ads")
public class Ad{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long adId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", unique = false)
    private Category category;

    private String title;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar duration;

    // private image?


    public Ad(User user, Category category, String title, String description, Calendar duration) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public long getAdId() { return adId; }

    public void setAdId(int adId) { this.adId = adId; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category;}

    public String getTitle() { return title;}

    public void setTitle(String title) {this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Calendar getDuration() { return duration; }

    public void setDuration(Calendar duration) { this.duration = duration; }

}
