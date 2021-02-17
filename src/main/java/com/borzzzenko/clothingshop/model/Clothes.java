package com.borzzzenko.clothingshop.model;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Double getPrice() {
        return price;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public void setCategory(ClothesCategory category) {
        this.category = category;
    }

    public void setColor(Color color) {
        this.color = color;
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
