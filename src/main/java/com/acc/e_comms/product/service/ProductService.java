package com.acc.e_comms.product.service;

import com.acc.e_comms.product.dto.ProductRequest;
import com.acc.e_comms.product.dto.ProductResponse;

import java.util.List;


public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
