package com.ecommerceapp.services.implementations;

import com.ecommerceapp.models.Category;
import com.ecommerceapp.services.interfaces.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {

        Category ctg = categories.stream()
                .filter(c -> c.getCategoryName().equals(category.getCategoryName()))
                .findFirst().orElse(null);

        if(ctg != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");

        categories.add(category);
        return "Category Added Successfully !";
    }

    @Override
    public String deleteCategory(Long CategoryId) {

        Category ctg = categories.stream()
                .filter(c -> c.getCategoryId().equals(CategoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                           "Resource Don't Exist!"));

        categories.remove(ctg);
        return "Category With ID: %d Deleted Successfully !"
                .formatted(CategoryId);
    }

    @Override
    public Category updateCategory(Category category, Long CategoryId) {

        Optional<Category> ctg = categories.stream()
                .filter(c -> c.getCategoryId().equals(CategoryId))
                .findFirst();


        if (ctg.isPresent()) {
            Category existCategory = ctg.get();
            existCategory.setCategoryName(category.getCategoryName());
            return existCategory;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
        }
        
    }
}
