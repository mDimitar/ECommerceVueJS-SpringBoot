package com.ecommerce.onlineshopapp.Service;

import com.ecommerce.onlineshopapp.Model.Category;
import com.ecommerce.onlineshopapp.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Long id, Category category){
        Category c = categoryRepository.findById(id).get();
        c.setCategoryName(category.getCategoryName());
        c.setDescription(category.getDescription());
        c.setImageURL(category.getImageURL());
        categoryRepository.save(c);
    }
    public boolean checkIfFoundById(Long id){
        return categoryRepository.findById(id).isPresent();
    }

    public void deleteCategory(Long id){
        Category c = categoryRepository.findById(id).get();
        categoryRepository.delete(c);
    }

}
