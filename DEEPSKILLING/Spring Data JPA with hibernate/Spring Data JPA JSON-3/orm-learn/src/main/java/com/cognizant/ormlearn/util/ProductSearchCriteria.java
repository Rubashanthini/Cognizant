package com.cognizant.ormlearn.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ==========================================================
 * HANDS-ON 6 :: Criteria Query support class
 * ==========================================================
 * Holds the OPTIONAL filters a client may supply for a dynamic
 * product search. Any field left null is simply skipped when
 * the Specification/Predicate list is built in ProductService.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchCriteria {

    private Integer ram;
    private String cpu;
    private Double weight;
    private String os;
    private Double rating;
    private Double minPrice;
    private Double maxPrice;
}
