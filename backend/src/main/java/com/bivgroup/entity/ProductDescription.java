package com.bivgroup.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductDescription extends PanacheEntityBase {

    @Id
    private Long productDescriptionId;

    @ManyToOne
    @JoinColumn(name = "productId")
    Product product;

    private String fieldName;

    private String fieldSysName;

    private String fieldDescription;

    public Long getProductDescriptionId() {
        return productDescriptionId;
    }

    public void setProductDescriptionId(Long productDescriptionId) {
        this.productDescriptionId = productDescriptionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldSysName() {
        return fieldSysName;
    }

    public void setFieldSysName(String fieldSysName) {
        this.fieldSysName = fieldSysName;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }
}
