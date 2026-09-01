package com.acc.e_comms.product.repository;

import com.acc.e_comms.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
