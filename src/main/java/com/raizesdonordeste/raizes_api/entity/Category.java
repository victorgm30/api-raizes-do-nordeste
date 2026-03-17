package com.raizesdonordeste.raizes_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //O ID é gerado automaticamente pelo banco de dados
    private Long id;

    private String name;

    private String description;

}
