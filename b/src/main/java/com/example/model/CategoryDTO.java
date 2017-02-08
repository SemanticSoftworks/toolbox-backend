package com.example.model;

import java.util.List;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */
public class CategoryDTO {
    private Long categoryId;
    private String name;
    private List<AdDTO> ads;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdDTO> getAds() {
        return ads;
    }

    public void setAds(List<AdDTO> ads) {
        this.ads = ads;
    }
}
