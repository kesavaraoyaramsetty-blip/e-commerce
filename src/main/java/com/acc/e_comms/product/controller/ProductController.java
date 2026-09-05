package com.acc.e_comms.product.controller;

import com.acc.e_comms.product.dto.ProductRequest;
import com.acc.e_comms.product.dto.ProductResponse;
import com.acc.e_comms.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request) {

        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {

        return productService.getProduct(id);
    }

    //single column sorting
//    @GetMapping
//    public List<ProductResponse> getAllProducts(@RequestParam String sortBy,
//                                                @RequestParam String direction) {
//
//        return productService.getAllProducts(sortBy,direction);
//    }

    //multi column sorting
    @GetMapping
    public List<ProductResponse> getAllProducts(@RequestParam List<String> sort) {

        return productService.getAllProducts(sort);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }
}
