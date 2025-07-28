package com.ecommerceapp.services.implementations;

import com.ecommerceapp.exceptions.APIException;
import com.ecommerceapp.exceptions.ResourceNotFoundException;
import com.ecommerceapp.models.Category;
import com.ecommerceapp.payload.CategoryDTO;
import com.ecommerceapp.payload.CategoryResponse;
import com.ecommerceapp.repositories.CategoryRepository;
import com.ecommerceapp.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

//    private final List<Category> categories = new ArrayList<>();

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder =
                sortOrder.equalsIgnoreCase("asc") ?
                        Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = repository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

        if(categories.isEmpty()) {
            throw new APIException("No category Created till now !");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryResponse.isLastPage());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = mapper.map(categoryDTO, Category.class);

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

        Category savedCtg = repository.save(category);
        return mapper.map(savedCtg, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long CategoryId) {

        Category category = repository.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));

//        List<Category> categories = repository.findAll();
//
//        Category ctg = categories.stream()
//                .filter(c -> c.getCategoryId().equals(CategoryId))
//                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));

        repository.deleteById(CategoryId);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long CategoryId) {

//        Optional<Category> savedCategory = repository.findById(CategoryId);

        Category category = mapper.map(categoryDTO, Category.class);

        Category ctg = repository.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", CategoryId));

        category.setCategoryId(CategoryId);

        ctg = repository.save(category);
        return mapper.map(ctg, CategoryDTO.class);
        
    }
}
