package com.example.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dani on 2017-02-06.
 */

@Entity(name = "Category")
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Ad> ads;

    public Category(){}
    public Category(long categoryId, String name, List<Ad> ads) {
        this.categoryId = categoryId;
        this.name = name;
        this.ads = ads;
    }

    public Category(){}

    public Long  getCategoryId() { return categoryId; }

    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Ad> getAds() { return ads; }

    public void setUser(List<Ad> ads) { this.ads = ads; }
}
