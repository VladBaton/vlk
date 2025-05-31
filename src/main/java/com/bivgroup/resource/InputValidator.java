package com.bivgroup.resource;

import com.bivgroup.entity.Account;
import com.bivgroup.entity.Contract;
import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.*;
import com.bivgroup.repository.AccountRepository;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import com.bivgroup.repository.VerificationCodeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class InputValidator {

    @Inject
    ContractRepository contractRepository;

    @Inject
    InsurerRepository insurerRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    VerificationCodeRepository verificationCodeRepository;

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
        Contract contract = contractRepository.findByContractNumber(request.getContractNumber())
                .orElseThrow(() -> new HandledServiceException(1L, "Контракт не найден"));
        if (Objects.nonNull(contract.getInsurer().getAccount())) {
            throw new HandledServiceException(5L, "Аккаунт уже создан, логин: " + contract.getInsurer().getAccount().getLogin());
        }
    }

    public void validateCreateVerificationCodeRequest(@Valid CreateVerificationCodeRequest request) throws HandledServiceException {
        Contract contract = contractRepository.findByContractNumber(request.getContractNumber())
                .orElseThrow(() -> new HandledServiceException(1L, "Контракт не найден"));
        if (Objects.nonNull(contract.getInsurer().getAccount())) {
            throw new HandledServiceException(5L, "Аккаунт уже создан, логин: " + contract.getInsurer().getAccount().getLogin());
        }

        if (verificationCodeRepository
                .findActiveVerificationCodeByInsurerId(contract.getInsurer().getInsurerId()).isPresent()) {
            throw new HandledServiceException(5L, "Код подтверждения уже выслан");
        }
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

    public void validateAuthorizationRequest(@Valid AuthorizationRequest request) throws HandledServiceException {
        Account account = accountRepository.findByLogin(request.getUserLogin()).orElseThrow(() ->
                new HandledServiceException(
                        4L,
                        "Неверный логин или пароль"
                )
        );

        if (!BCrypt.checkpw(request.getPassword(), account.getPassword())) {
            throw new HandledServiceException(
                    4L,
                    "Неверный логин или пароль"
            );
        }

        if (!account.getIsActive()) {
            throw new HandledServiceException(
                    5L,
                    "Аккаунт неактивен"
            );
        }
    }

    public void validateDeleteAccountRequest(@Valid DeleteAccountRequest request) throws HandledServiceException {
        Account account = accountRepository.findByLogin(request.getLogin()).orElseThrow(() ->
                new HandledServiceException(
                        4L,
                        "Неверный логин или пароль"
                )
        );
    }
}
