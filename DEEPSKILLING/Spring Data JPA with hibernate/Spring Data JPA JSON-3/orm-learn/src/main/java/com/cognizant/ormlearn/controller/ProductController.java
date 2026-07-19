package com.cognizant.ormlearn.controller;

import com.cognizant.ormlearn.model.Product;
import com.cognizant.ormlearn.service.ProductService;
import com.cognizant.ormlearn.util.ProductSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * HANDS-ON 6 :: Dynamic Product Search
     * All query params are optional, e.g.:
     *   /api/products/search?ram=16&os=Windows%2011&minPrice=50000&maxPrice=120000
     */
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) Integer ram,
            @RequestParam(required = false) String cpu,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) String os,
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        ProductSearchCriteria criteria = new ProductSearchCriteria(ram, cpu, weight, os, rating, minPrice, maxPrice);
        return productService.searchProducts(criteria);
    }
}
