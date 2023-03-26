package com.ecommerce.onlineshopapp.Controllers;

import com.ecommerce.onlineshopapp.ApiResponses.ApiResponse;
import com.ecommerce.onlineshopapp.DataTransferObject.ProductDTO;
import com.ecommerce.onlineshopapp.Model.Category;
import com.ecommerce.onlineshopapp.Model.Product;
import com.ecommerce.onlineshopapp.Repository.CategoryRepository;
import com.ecommerce.onlineshopapp.Repository.ProductRepository;
import com.ecommerce.onlineshopapp.Service.CategoryService;
import com.ecommerce.onlineshopapp.Service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductsController(ProductsService productsService, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.productsService = productsService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        Optional<Category> category = categoryRepository.findById(productDTO.getCategoryID());
        if(!category.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(
                    false,
                    "Product is not succesfully created."),
                    HttpStatus.BAD_REQUEST);
        }
        productsService.createProduct(productDTO,category.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                "Product is succesfully created."),
                HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> products = productsService.listProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(
                    false,
                    "Product does not exist."),
                    HttpStatus.NOT_FOUND);
        }
        productsService.updateProduct(productDTO,id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                "Product is successfully updated."),
                HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(
                    false,
                    "The requested product cannot be found."
            ), HttpStatus.NOT_FOUND);
        }
        productsService.deleteProduct(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                "The product has been successfully deleted."),
                HttpStatus.CREATED);
    }
}
