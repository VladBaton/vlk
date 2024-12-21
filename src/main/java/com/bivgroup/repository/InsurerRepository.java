package com.bivgroup.repository;

import com.bivgroup.entity.Insurer;
import com.bivgroup.entity.Notification;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InsurerRepository implements PanacheRepository<Insurer> {

    public Optional<Insurer> findByInsurerId(Long insurerId) {
        return findByIdOptional(insurerId);
    }
}
