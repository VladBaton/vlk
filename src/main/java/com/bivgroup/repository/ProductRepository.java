package com.bivgroup.repository;

import com.bivgroup.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Optional<Product> findBySysName(String prodSysName) {
        return find("productSysName", prodSysName).firstResultOptional();
    }
}
