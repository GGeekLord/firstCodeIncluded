package com.ecommerceapp.controllers;

import com.ecommerceapp.models.Category;
import com.ecommerceapp.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }



    @PostMapping("/public/categories")
    public ResponseEntity<String> postCategory(@RequestBody Category category) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));

        } catch(ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }


    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {

        try {
            // return categoryService.deleteCategory(categoryId); --- Older Version !
            // return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
            // return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(categoryId));

            return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);

        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
        }
    }

    @PutMapping("/admin/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable("categoryId") Long  categoryId) {

        try {
            Category savedCtg = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category with categoryId: " + categoryId, HttpStatus.OK);

        } catch(ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
        }
    }
}
