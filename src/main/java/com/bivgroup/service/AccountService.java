package com.bivgroup.service;

import com.bivgroup.constant.Constants;
import com.bivgroup.entity.Account;
import com.bivgroup.entity.Contract;
import com.bivgroup.entity.Insurer;
import com.bivgroup.entity.VerificationCode;
import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.mapper.EntityToPojoMapper;
import com.bivgroup.pojo.request.*;
import com.bivgroup.repository.AccountRepository;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import com.bivgroup.repository.VerificationCodeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AccountService {

    @Inject
    ContractRepository contractRepository;

    @Inject
    InsurerRepository insurerRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    VerificationCodeRepository verificationCodeRepository;

    @Inject
    FormResponseService formResponseService;

    @Inject
    HandbookService handbookService;

    @Transactional
    public Response createVerificationCode(CreateVerificationCodeRequest request) {
        Contract contract = contractRepository.findByContractNumber(request.getContractNumber()).orElseThrow();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setExpirationDate(new Date(new Date().getTime() + 600000L));
        verificationCode.setIsUsed(false);
        verificationCode.setVerificationCode(String.valueOf(UUID.randomUUID()));
        verificationCode.setInsurer(contract.getInsurer());
        verificationCodeRepository.persist(verificationCode);

        contract.getInsurer().getVerificationCodes().add(verificationCode);


        return Response
                .ok(formResponseService.formBaseResponse(request, 0L, "Проверочный код успешно создан"))
                .build();
    }

    public Response validateVerificationCode(@Valid ValidateVerificationCodeRequest request) throws HandledServiceException {

        Contract contract = contractRepository.findByContractNumber(request.getContractNumber())
                .orElseThrow(() -> new HandledServiceException(5L, String.format("Контракт с номером %s не найден", request.getContractNumber())));

        Optional<VerificationCode> verificationCode = verificationCodeRepository
                .findActiveVerificationCodeByInsurerId(contract.getInsurer().getInsurerId());

        if (verificationCode.isPresent() && Objects.equals(verificationCode.get().getVerificationCode(), request.getVerificationCode())) {
            return Response.ok(formResponseService.formBaseResponse(request, 0L, "Обработан успешно")).build();
        } else {
            return Response.ok(formResponseService.formBaseResponse(request, 5L, "Неверный код")).build();
        }
    }

    @Transactional
    public Response createAccount(CreateAccountRequest request) {
        Contract contract = contractRepository.findByContractNumber(request.getContractNumber()).orElseThrow();

        Account account = createAccountFromRequest(request);
        accountRepository.persist(account);
        contract.getInsurer().setAccount(account);

        return Response
                .ok(formResponseService.formBaseResponse(request, 0L, "Аккаунт создан"))
                .build();
    }

    public Response updateAccount(UpdateAccountRequest request) {
        return Response
                .ok(formResponseService.formBaseResponse(request, 0L, "Аккаунт создан"))
                .build();
    }

    public Response getUserData(GetUserDataRequest request) {
        Insurer insurer = insurerRepository.findByInsurerId(request.getInsurerId()).orElseThrow();
        return Response
                .ok(
                        BaseResponseMapper
                                .INSTANCE
                                .toGetUserDataResponse(
                                        request,
                                        EntityToPojoMapper
                                                .INSTANCE
                                                .toInsurerPojo(insurer, handbookService),
                                        0L,
                                        Constants.SUCCESSFULLY_PROCESSED_STATUS
                                )
                )
                .build();
    }

    @Transactional
    public Response deleteAccount(DeleteAccountRequest request) {

        Insurer insurer = insurerRepository.findByLogin(request.getLogin()).orElseThrow();
        accountRepository.delete(insurer.getAccount());
        insurer.setAccount(null);
        insurerRepository.getEntityManager().merge(insurer);

        return Response
                .ok(formResponseService.formBaseResponse(request, 0L, "Аккаунт удалён"))
                .build();
    }

    public Response disableAccounts(DisableAccountsRequest request) {
        for (Long id: request.getAccountIds()) {
            try {
                setIsActiveValue(id, false);
            } catch (Exception ignored) {
            }
        }
        return Response
                .ok(formResponseService.formBaseResponse(request, 0L, "Обработан успешно"))
                .build();
    }

    public Response enableAccounts(EnableAccountsRequest request) {
        for (Long id: request.getAccountIds()) {
            try {
                setIsActiveValue(id, true);
            } catch (Exception ignored) {
            }
        }
        return Response
                .ok(formResponseService.formBaseResponse(request, 0L, "Обработан успешно"))
                .build();
    }

    @Transactional
    public void setIsActiveValue(Long accountId, boolean isActive) {
        Account account = accountRepository.findById(accountId);
        if (Objects.isNull(account)) {
            return;
        }
        account.setIsActive(isActive);
    }

    private Account createAccountFromRequest(CreateAccountRequest request) {
        Account account = new Account();
        account.setLogin(request.getLogin());
        account.setPassword(hashPassword(request.getPassword()));
        account.setRole("user");
        return account;
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
