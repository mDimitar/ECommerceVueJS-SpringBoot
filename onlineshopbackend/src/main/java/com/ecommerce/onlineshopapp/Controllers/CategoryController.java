package com.ecommerce.onlineshopapp.Controllers;

import com.ecommerce.onlineshopapp.ApiResponses.ApiResponse;
import com.ecommerce.onlineshopapp.Model.Category;
import com.ecommerce.onlineshopapp.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                "The category has been successfully added!"),
                HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/list")
    public List<Category> listAllCategories(){
        return categoryService.listAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
        if(!categoryService.checkIfFoundById(id)){
            return new ResponseEntity<ApiResponse>(new ApiResponse(
                    false,
                    "The requested category cannot be found."
            ), HttpStatus.NOT_FOUND);
        }
        categoryService.updateCategory(id,category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                "The category has been successfully updated."),
                HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        if(!categoryService.checkIfFoundById(id)){
            return new ResponseEntity<ApiResponse>(new ApiResponse(
                    false,
                    "The requested category cannot be found."
            ), HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                "The category has been successfully deleted."),
                HttpStatus.CREATED);
    }
}
