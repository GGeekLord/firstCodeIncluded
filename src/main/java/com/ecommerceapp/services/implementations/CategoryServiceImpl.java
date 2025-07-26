package com.ecommerceapp.services.implementations;

import com.ecommerceapp.models.Category;
import com.ecommerceapp.repositories.CategoryRepository;
import com.ecommerceapp.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

//    private final List<Category> categories = new ArrayList<>();

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public String createCategory(Category category) {

        List<Category> categories = repository.findAll();

        Category ctg = categories.stream()
                .filter(c -> c.getCategoryName().equals(category.getCategoryName()))
                .findFirst().orElse(null);

        if(ctg != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");

        repository.save(category);
        return "Category Added Successfully !";
    }

    @Override
    public String deleteCategory(Long CategoryId) {

        List<Category> categories = repository.findAll();

        Category ctg = categories.stream()
                .filter(c -> c.getCategoryId().equals(CategoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                           "Resource Don't Exist!"));


        repository.deleteById(CategoryId);
        return "Category With ID: %d Deleted Successfully !"
                .formatted(CategoryId);
    }

    @Override
    public Category updateCategory(Category category, Long CategoryId) {

        List<Category> categories = repository.findAll();

        Optional<Category> ctg = categories.stream()
                .filter(c -> c.getCategoryId().equals(CategoryId))
                .findFirst();


        if (ctg.isPresent()) {
            Category existCategory = ctg.get();
            existCategory.setCategoryName(category.getCategoryName());
            return repository.save(existCategory);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
        }
        
    }
}
