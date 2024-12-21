package com.bivgroup.service;

import com.bivgroup.constant.Constants;
import com.bivgroup.entity.Insurer;
import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.mapper.EntityToPojoMapper;
import com.bivgroup.pojo.request.AuthorizationRequest;
import com.bivgroup.pojo.request.CreateAccountRequest;
import com.bivgroup.pojo.request.GetUserDataRequest;
import com.bivgroup.pojo.request.UpdateAccountRequest;
import com.bivgroup.repository.InsurerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AccountService {

    @Inject
    InsurerRepository insurerRepository;

    @Inject
    FormResponseService formResponseService;

    public Response createAccount(CreateAccountRequest request) {
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

    public Response authorize(AuthorizationRequest request) {
        return Response
                .ok(
                        BaseResponseMapper
                                .INSTANCE
                                .toBaseResponse(request, 0L, "Авторизован успешно"))
                .build();
    }
}
