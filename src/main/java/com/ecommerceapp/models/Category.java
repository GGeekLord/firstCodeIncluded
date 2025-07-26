package com.ecommerceapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long CategoryId;

    private String CategoryName;

    public Category() {
    }

    public Category(Long CategoryId) {

        this.CategoryId = CategoryId;
    }

    public Category(Long CategoryId, String CategoryName) {

        this.CategoryId = CategoryId;
        this.CategoryName = CategoryName;
    }


    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }

    @Override
    public String toString() {
        return "%s: [Id: %s || Name: %s]"
                .formatted(getClass().getSimpleName(), getCategoryId(), getCategoryName());
    }

}
