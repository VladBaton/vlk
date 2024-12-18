package com.bivgroup.repository;

import com.bivgroup.entity.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {

    public Optional<Payment> findByPayId(Long paymentId) {
        return find("paymentId", paymentId).singleResultOptional();
    }

    public List<Payment> findByContractId(Long contractId) {
        return find("contractId", contractId).list();
    }

}
