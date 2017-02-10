package com.example.model;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */
public class CategoryDTO {
    private Long categoryId;
    private String name;

    public CategoryDTO(Long categoryId, String name){
        this.categoryId=categoryId;
        this.name = name;
    }

    public CategoryDTO(){}

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
