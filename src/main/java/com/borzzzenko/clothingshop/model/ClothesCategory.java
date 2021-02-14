package com.borzzzenko.clothingshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clothescategory")
public class ClothesCategory {
    @Id
    @GeneratedValue
    @Column(name = "PK_ClothesCategory")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    public ClothesCategory() {
    }

    public ClothesCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ClothesCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
