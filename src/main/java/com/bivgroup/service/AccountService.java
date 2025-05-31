package com.bivgroup.service;

import com.bivgroup.constant.Constants;
import com.bivgroup.entity.Account;
import com.bivgroup.entity.Contract;
import com.bivgroup.entity.Insurer;
import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.mapper.EntityToPojoMapper;
import com.bivgroup.pojo.request.*;
import com.bivgroup.repository.AccountRepository;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

@ApplicationScoped
public class AccountService {

    @Inject
    ContractRepository contractRepository;

    @Inject
    InsurerRepository insurerRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    FormResponseService formResponseService;

    @Inject
    HandbookService handbookService;

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
