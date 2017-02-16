package com.example.domain;

import javax.persistence.*;

/**
 * Created by Teddy on 2017-02-10.
 */

@Entity(name = "Photo")
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Lob()
    private byte[] photobyte;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Photo(){}

    public Photo(long id,byte[] photobyte, Ad ad) {
        this.photobyte = photobyte;
        this.ad = ad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhotobyte() {
        return photobyte;
    }

    public void setPhotobyte(byte[] photobyte) {
        this.photobyte = photobyte;
    }

    public Ad getAd() {
        return ad;
    }
}
