package com.obsm.web.model;

import com.obsm.web.model.constant.ProductCategory;
import com.obsm.web.model.constant.ProductModel;
import jakarta.persistence.*;

import java.util.Objects;

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
    private Double price;
    @Column(name = "main_img_url")
    private String mainImgUrl;
    @Column(name = "second_img_url")
    private String secondImgUrl;
    @Column(name = "third_img_url")
    private String thirdImgUrl;

    public Product() {
    }

    public Product(String name, ProductModel model, ProductCategory category, String description, Double price, String mainImgUrl, String secondImgUrl, String thirdImgUrl) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.description = description;
        this.price = price;
        this.mainImgUrl = mainImgUrl;
        this.secondImgUrl = secondImgUrl;
        this.thirdImgUrl = thirdImgUrl;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getSecondImgUrl() {
        return secondImgUrl;
    }

    public void setSecondImgUrl(String secondImgUrl) {
        this.secondImgUrl = secondImgUrl;
    }

    public String getThirdImgUrl() {
        return thirdImgUrl;
    }

    public void setThirdImgUrl(String thirdImgUrl) {
        this.thirdImgUrl = thirdImgUrl;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
