package com.ecommerceapp.services.implementations;

import com.ecommerceapp.exceptions.APIException;
import com.ecommerceapp.exceptions.ResourceNotFoundException;
import com.ecommerceapp.models.Category;
import com.ecommerceapp.repositories.CategoryRepository;
import com.ecommerceapp.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

        List<Category> categories = repository.findAll();

        if(categories.isEmpty()) {
            throw new APIException("No category Created till now !");
        }

        return repository.findAll();
    }

    @Override
    public String createCategory(Category category) {

        Category savedCategory = repository.findByCategoryName(category.getCategoryName());

        if(savedCategory != null) {

            throw new APIException("Category with name %s is already exist !"
                    .formatted(category.getCategoryName()));
        }

        /// the traditional way for preventing duplication
//        List<Category> categories = repository.findAll();
//
//        Category ctg = categories.stream()
//                .filter(c -> c.getCategoryName().equals(category.getCategoryName()))
//                .findFirst().orElse(null);
//
//        if(ctg != null)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");

        repository.save(category);
        return "Category Added Successfully !";
    }

    @Override
    public String deleteCategory(Long CategoryId) {

        Category category = repository.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));

//        List<Category> categories = repository.findAll();
//
//        Category ctg = categories.stream()
//                .filter(c -> c.getCategoryId().equals(CategoryId))
//                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));

        repository.deleteById(CategoryId);
        return "Category With ID: %d Deleted Successfully !"
                .formatted(CategoryId);
    }

    @Override
    public Category updateCategory(Category category, Long CategoryId) {

//        Optional<Category> savedCategory = repository.findById(CategoryId);

        Category ctg = repository.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));

        category.setCategoryId(CategoryId);
        ctg =  repository.save(category);
        return ctg;
        
    }
}
