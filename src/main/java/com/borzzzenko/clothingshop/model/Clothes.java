package com.borzzzenko.clothingshop.model;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    @GeneratedValue
    @Column(name = "PK_Clothes")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "ImagePath")
    private String imagePath;

    @Column(name = "Sizes", nullable = false)
    private String sizes;

    @ManyToOne
    @JoinColumn(name="PK_ClothesCategory")
    private ClothesCategory category;

    @ManyToOne
    @JoinColumn(name="PK_Color")
    private Color color;

    public Clothes() {
    }

    public Clothes(Long id, String name, String description, Double price, String imagePath, String sizes, ClothesCategory category, Color color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.sizes = sizes;
        this.category = category;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price.toString() + " р.";
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSizes() {
        return sizes;
    }

    public ClothesCategory getCategory() {
        return category;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                ", sizes='" + sizes + '\'' +
                ", category=" + category +
                ", color=" + color +
                '}';
    }
}
