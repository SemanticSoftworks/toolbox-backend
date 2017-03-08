package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO2 {
    private Long categoryId;
    private String name;

    public CategoryDTO2(@JsonProperty("categoryId") Long categoryId, @JsonProperty("name") String name){
        this.categoryId=categoryId;
        this.name = name;
    }

    public CategoryDTO2(){}

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
}
