package com.borzzzenko.clothingshop.model;

import javax.persistence.*;

@Entity
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue
    @Column(name = "PK_Color")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    public Color() {
    }

    public Color(String name) {
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
        return "Color{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
