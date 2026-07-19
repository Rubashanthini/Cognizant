package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Product;
import com.cognizant.ormlearn.util.ProductSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * HANDS-ON 6 :: Criteria Query
 * ==========================================================
 *
 * Concepts:
 *  - CriteriaBuilder : factory for constructing CriteriaQuery, Predicate,
 *    Expression and other Criteria API objects. Obtained from the
 *    EntityManager.
 *  - CriteriaQuery<T> : represents the top-level, type-safe query object
 *    being built (SELECT ... FROM ... WHERE ...) for result type T.
 *  - Root<T> : represents the FROM clause; the root entity the query
 *    is selecting from, and the source of attribute paths (root.get("field")).
 *  - Predicate : represents a WHERE-clause condition (equals, greaterThan,
 *    like, between, etc). Multiple predicates are combined with
 *    cb.and(...) / cb.or(...).
 *  - TypedQuery<T> : the executable query object obtained from
 *    entityManager.createQuery(criteriaQuery), analogous to a
 *    Query object for JPQL but type-safe and constructed
 *    programmatically instead of as a query string.
 *
 * This class builds a fully dynamic product search: each filter
 * (RAM, CPU, Weight, OS, Rating, Price range) is OPTIONAL - a
 * Predicate is added to the list only if the corresponding
 * criteria field is non-null, and all supplied predicates are
 * ANDed together at the end.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> searchProducts(ProductSearchCriteria criteria) {
        logger.info("Building dynamic Criteria API query for filters: {}", criteria);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getRam() != null) {
            predicates.add(cb.equal(product.get("ram"), criteria.getRam()));
        }
        if (criteria.getCpu() != null && !criteria.getCpu().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("cpu")), "%" + criteria.getCpu().toLowerCase() + "%"));
        }
        if (criteria.getWeight() != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("weight"), criteria.getWeight()));
        }
        if (criteria.getOs() != null && !criteria.getOs().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("os")), "%" + criteria.getOs().toLowerCase() + "%"));
        }
        if (criteria.getRating() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("rating"), criteria.getRating()));
        }
        if (criteria.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), criteria.getMinPrice()));
        }
        if (criteria.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), criteria.getMaxPrice()));
        }

        cq.select(product).where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Product> typedQuery = entityManager.createQuery(cq);
        List<Product> results = typedQuery.getResultList();

        logger.info("Dynamic product search returned {} result(s)", results.size());
        return results;
    }
}
