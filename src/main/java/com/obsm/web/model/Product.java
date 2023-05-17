package com.obsm.web.model;

import com.obsm.web.model.constant.ProductCategory;
import com.obsm.web.model.constant.ProductModel;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private ProductModel model;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ProductCategory category;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private String price;

    public Product() {
    }

    public Product(String name, ProductModel model, ProductCategory category, String description, String price) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductModel getModel() {
        return model;
    }

    public void setModel(ProductModel model) {
        this.model = model;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
