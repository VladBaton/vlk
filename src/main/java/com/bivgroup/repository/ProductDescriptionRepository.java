package com.bivgroup.repository;

import com.bivgroup.entity.ProductDescription;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProductDescriptionRepository implements PanacheRepository<ProductDescription> {

    @Inject
    ProductRepository productRepository;

    public List<ProductDescription> getProductDescription(Long productId) {
        return find("product", productRepository.findById(productId)).list();
    }
}
