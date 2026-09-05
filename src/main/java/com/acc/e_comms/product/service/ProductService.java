package com.acc.e_comms.product.service;

import com.acc.e_comms.product.dto.ProductRequest;
import com.acc.e_comms.product.dto.ProductResponse;

import java.util.List;


public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProduct(Long id);

    //implementing sorting functionality
    //List<ProductResponse> getAllProducts(String sortBy, String direction);

    //multi column sorting
    List<ProductResponse> getAllProducts(List<String> sort);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
