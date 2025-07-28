package com.ecommerceapp.controllers;

import com.ecommerceapp.config.AppConstants;
import com.ecommerceapp.models.Category;
import com.ecommerceapp.payload.CategoryDTO;
import com.ecommerceapp.payload.CategoryResponse;
import com.ecommerceapp.services.interfaces.CategoryService;
import jakarta.validation.Valid;
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

    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(defaultValue = "Write SomeThing as a Param !",
                                                            required     = false) String valueThere) {

        String message = "|> The message : %s \n".formatted(valueThere);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getCategories(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                          @RequestParam(name = "pageSize"  , defaultValue = AppConstants.PAGE_SIZE,   required = false)  Integer pageSize,
                                                          @RequestParam(name = "sortBy"    , defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false)    String sortBy,
                                                          @RequestParam(name = "sortOrder" , defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        return ResponseEntity.ok(categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder));
    }


    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> postCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDTO));
    }


    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(categoryId));
    }

    @PutMapping("/admin/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable("categoryId") Long  categoryId) {

        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }
}
