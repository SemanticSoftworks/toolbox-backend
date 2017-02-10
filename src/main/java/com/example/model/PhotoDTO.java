package com.example.model;

/**
 * Created by Teddy on 2017-02-10.
 */
public class PhotoDTO {
    private long id;
    private byte[] imgByte;
    private long adId;

    public PhotoDTO(long id, byte[] imgByte, long adId) {
        this.id = id;
        this.imgByte = imgByte;
        this.adId = adId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public long getAdId() {
        return adId;
    }


}
