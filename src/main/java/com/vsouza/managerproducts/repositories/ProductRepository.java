package com.vsouza.managerproducts.repositories;

import com.vsouza.managerproducts.dto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
