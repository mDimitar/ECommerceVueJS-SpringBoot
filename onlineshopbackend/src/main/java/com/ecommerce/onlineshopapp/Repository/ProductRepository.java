package com.ecommerce.onlineshopapp.Repository;

import com.ecommerce.onlineshopapp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
