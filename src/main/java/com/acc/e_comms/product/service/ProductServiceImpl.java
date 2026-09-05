package com.acc.e_comms.product.service;

import com.acc.e_comms.exception.ProductNotFoundException;
import com.acc.e_comms.product.dto.ProductRequest;
import com.acc.e_comms.product.dto.ProductResponse;
import com.acc.e_comms.product.entity.Product;
import com.acc.e_comms.product.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        //product.setImageUrl(request.getImageUrl());

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse getProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        return mapToResponse(product);
    }

    //multi column sorting

    @Override
    public List<ProductResponse> getAllProducts(List<String> sort) {
        List<Sort.Order> sorts = sort.stream()
                .map(value->{
                    String[] values = value.split(",");
                    String field = values[0];
                    String direction = values[1];

                    return new Sort.Order(
                            Sort.Direction.fromString(direction),
                            field
                    );

                }).toList();
        Sort sorting = Sort.by(sorts);
        List<Product> products = productRepository.findAll(sorting);
        return products.stream().map(this::mapToResponse).toList();
    }

    //single column sorting
//    @Override
//    public List<ProductResponse> getAllProducts(String sortBy, String direction) {
//
//        Sort sort = Sort.by(
//                Sort.Direction.fromString(direction),
//                sortBy
//        );
//        //List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
//        List<Product> products = productRepository.findAll(sort);
//
//        return products.stream()
//                .map(this::mapToResponse)
//                .toList();
//    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        //product.setImageUrl(request.getImageUrl());

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        //response.setImageUrl(product.getImageUrl());

        return response;
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

}
