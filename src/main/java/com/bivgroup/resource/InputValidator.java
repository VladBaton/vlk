package com.bivgroup.resource;

import com.bivgroup.entity.Contract;
import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.*;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.Optional;

@ApplicationScoped
public class InputValidator {

    @Inject
    ContractRepository contractRepository;

    @Inject
    InsurerRepository insurerRepository;

    public void validateGetNotificationsByContractNumberRequest(@Valid GetNotificationsByContractNumberRequest request) throws HandledServiceException {
        Optional<Contract> contract = contractRepository.findByContractNumber(request.getContractNumber());
        contract.orElseThrow(() -> new HandledServiceException(1L, "Контракт не найден"));
    }

    public void validateGetNotificationsByPeriodRequest(@Valid GetNotificationsByPeriodRequest request) throws HandledServiceException {
        Date todayDate = new Date();
        if (request.getDateFrom().after(todayDate)) {
            throw new HandledServiceException(2L, "Неверно указан период");
        }
    }

    public void validateGetNotificationsByClientRequest(@Valid GetNotificationsByClientRequest request) throws HandledServiceException {
        insurerRepository
                .findByInsurerId(request.getInsurerId())
                .orElseThrow(
                        () ->
                                new HandledServiceException(
                                        3L,
                                        String
                                                .format("Страхователь с id %d не найден", request.getInsurerId())
                                )
                );
    }

    public void validateCreateAccountRequest(@Valid CreateAccountRequest request) throws HandledServiceException {
        Optional<Contract> contract = contractRepository.findByContractNumber(request.getContractNumber());
        contract.orElseThrow(() -> new HandledServiceException(1L, "Контракт не найден"));
    }

    public void validateUpdateAccountRequest(@Valid UpdateAccountRequest request) {
        //Вставить сюда логику
    }

    public void validateGetUserDataRequest(@Valid GetUserDataRequest request) throws HandledServiceException {
        insurerRepository
                .findByInsurerId(request.getInsurerId())
                .orElseThrow(
                        () ->
                                new HandledServiceException(
                                        3L,
                                        String
                                                .format("Страхователь с id %d не найден", request.getInsurerId())
                                )
                );
    }

    public void validateAuthorizationRequest(@Valid AuthorizationRequest request) {
        //Вставить сюда логику проверки запроса
    }
}
