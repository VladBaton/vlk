package com.bivgroup.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product extends PanacheEntityBase {

    @Id
    @Column(name = "PRODUCTID")
    private Long productId;

    @Column(name = "PRODUCTSYSNAME",nullable = false, unique = true)
    private String productSysName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductDescription> productDescriptions;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductSysName() {
        return productSysName;
    }

    public void setProductSysName(String productSysName) {
        this.productSysName = productSysName;
    }

    public List<ProductDescription> getProductDescriptions() {
        return productDescriptions;
    }

    public void setProductDescriptions(List<ProductDescription> productDescriptions) {
        this.productDescriptions = productDescriptions;
    }
}
