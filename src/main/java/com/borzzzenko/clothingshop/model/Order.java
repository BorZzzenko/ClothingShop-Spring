package com.borzzzenko.clothingshop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Order")
    private Long id;

    @Column(name = "Size")
    private String size;

    @Column(name = "Count")
    private Integer count;

    @Column(name = "Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="PK_User")
    private User owner;

    @ManyToOne
    @JoinColumn(name="PK_Clothes")
    private Clothes clothes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Order () {}

    public Order(Long id, String size, Integer count, Date date, User owner, Clothes clothes) {
        this.id = id;
        this.size = size;
        this.count = count;
        this.date = date;
        this.owner = owner;
        this.clothes = clothes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", count=" + count +
                ", date=" + date +
                ", owner=" + owner +
                ", clothes=" + clothes +
                '}';
    }
}
