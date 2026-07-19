package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaSpecificationExecutor gives access to the Criteria-API-backed
 * findAll(Specification<Product>) methods used by HANDS-ON 6.
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
