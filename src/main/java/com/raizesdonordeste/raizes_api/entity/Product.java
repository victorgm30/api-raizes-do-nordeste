package com.raizesdonordeste.raizes_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O ID é gerado automaticamente pelo banco de dados
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("products") // Evita o loop infinito na serialização JSON
    private Category category;
}
