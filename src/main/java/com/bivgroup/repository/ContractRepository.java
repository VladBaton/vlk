package com.bivgroup.repository;

import com.bivgroup.entity.Contract;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ContractRepository implements PanacheRepository<Contract> {

    public Optional<Contract> findByContractId(Long contractId) {
        return find("contractId", contractId).singleResultOptional();
    }

    public Optional<Contract> findByContractNumber(String contractNumber) {
        return find("contractNumber", contractNumber).singleResultOptional();
    }

    public List<Contract> findByInsurerId(Long insurerId) {
        return find("insurer.insurerId=?1", insurerId).list();
    }
}
