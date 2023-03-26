package com.ecommerce.onlineshopapp.Service;

import com.ecommerce.onlineshopapp.DataTransferObject.ProductDTO;
import com.ecommerce.onlineshopapp.Model.Category;
import com.ecommerce.onlineshopapp.Model.Product;
import com.ecommerce.onlineshopapp.Repository.CategoryRepository;
import com.ecommerce.onlineshopapp.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductsService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createProduct(ProductDTO productDTO, Category category) {
        Product prod = new Product();
        prod.setName(productDTO.getName());
        prod.setDescription(productDTO.getDescription());
        prod.setImageURL(productDTO.getImageURL());
        prod.setCategory(category);
        prod.setPrice(productDTO.getPrice());
        productRepository.save(prod);

    }
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(ProductDTO productDTO, Long id) throws Exception {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()) {
            throw new Exception("Product cannot be found.");
        }
        foundProduct.get().setName(productDTO.getName());
        foundProduct.get().setImageURL(productDTO.getImageURL());
        foundProduct.get().setPrice(productDTO.getPrice());
        foundProduct.get().setDescription(productDTO.getDescription());
        Category updatedCategory = categoryRepository.findById(productDTO.getCategoryID()).get();
        foundProduct.get().setCategory(updatedCategory);
        return productRepository.save(foundProduct.get());
    }
    public Product deleteProduct(Long id) throws Exception{
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new Exception("Product cannot be found");
        }
        productRepository.delete(product.get());
        return product.get();
    }
}
