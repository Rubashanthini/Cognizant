package com.cognizant.ormlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;

    @Column(name = "ram")
    private Integer ram;

    @Column(name = "cpu", length = 100)
    private String cpu;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "os", length = 100)
    private String os;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "price")
    private Double price;
}
