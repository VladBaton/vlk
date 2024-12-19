package com.bivgroup.resource;

import com.bivgroup.entity.Contract;
import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
import com.bivgroup.repository.ContractRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.Optional;

@ApplicationScoped
public class InputValidator {

    @Inject
    ContractRepository contractRepository;

    public void validateGetNotificationsByContractNumberRequest(@Valid GetNotificationsByContractNumberRequest request) throws HandledServiceException {
        Optional<Contract> contract = contractRepository.findByContractNumber(request.getContractNumber());
        contract.orElseThrow(() -> new HandledServiceException(1L, "Контракт не найден"));
    }
}
