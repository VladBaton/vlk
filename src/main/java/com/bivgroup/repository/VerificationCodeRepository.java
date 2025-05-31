package com.bivgroup.repository;

import com.bivgroup.entity.VerificationCode;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class VerificationCodeRepository implements PanacheRepository<VerificationCode> {

    public Optional<VerificationCode> findActiveVerificationCodeByInsurerId(Long insurerId) {
        return find("insurer.insurerId=?1 and expirationDate > CURRENT_TIMESTAMP and isUsed = false", insurerId).firstResultOptional();
    }
}
