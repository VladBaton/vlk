package com.bivgroup.repository;

import com.bivgroup.entity.VerificationCode;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VerificationCodeRepository implements PanacheRepository<VerificationCode> {

}
