package com.ecommerceapp.services.interfaces;

import com.ecommerceapp.models.Category;
import com.ecommerceapp.payload.CategoryDTO;
import com.ecommerceapp.payload.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long CategoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long CategoryId);
}
