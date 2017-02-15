package com.example.domain;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Calendar;
import java.util.List;

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

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Photo.class)
    @JoinColumn(name = "ad_id")
    private List<Photo> photos;

    public Ad(){}
    public Ad(User user, Category category, String title, String description, Calendar duration, List<Photo> photos) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.photos = photos;
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void addPhoto(Blob photo)
    {
        //
    }

    public void removePhoto(int index)
    {
        //this.photos.remove(index);
    }

   public void clearPhotos()
   {
       //this.photos = new ArrayList<>();
   }
}
