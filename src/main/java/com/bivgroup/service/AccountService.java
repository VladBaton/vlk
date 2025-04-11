package com.bivgroup.service;

import com.bivgroup.constant.Constants;
import com.bivgroup.entity.Account;
import com.bivgroup.entity.Contract;
import com.bivgroup.entity.Insurer;
import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.mapper.EntityToPojoMapper;
import com.bivgroup.pojo.request.CreateAccountRequest;
import com.bivgroup.pojo.request.GetUserDataRequest;
import com.bivgroup.pojo.request.UpdateAccountRequest;
import com.bivgroup.repository.AccountRepository;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

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
                                                .toInsurerPojo(insurer),
                                        0L,
                                        Constants.SUCCESSFULLY_PROCESSED_STATUS
                                )
                )
                .build();
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
