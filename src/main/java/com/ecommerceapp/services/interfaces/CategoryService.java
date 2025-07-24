package com.ecommerceapp.services.interfaces;

import com.ecommerceapp.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();

    String createCategory(Category category);

    String deleteCategory(Long CategoryId);

    Category updateCategory(Category category, Long CategoryId);
}
