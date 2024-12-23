package com.bivgroup.resource;

import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.*;
import com.bivgroup.service.AccountService;
import com.bivgroup.service.FormResponseService;
import com.bivgroup.service.NotificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/vlk")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "REST методы продукта Виртуальный личный кабинет страхователя")
public class VLKResource {

    @Inject
    InputValidator inputValidator;

    @Inject
    NotificationService notificationService;

    @Inject
    AccountService accountService;

    @POST
    @Path("/notifications/getByContractNumber")
    public Response getNotificationsByContractNumber(GetNotificationsByContractNumberRequest request) throws Exception {
        inputValidator.validateGetNotificationsByContractNumberRequest(request);
        return notificationService.getNotificationsByContractNumber(request);
    }

    @POST
    @Path("/notifications/getByPeriod")
    public Response getNotificationsByPeriod(GetNotificationsByPeriodRequest request) throws HandledServiceException {
        inputValidator.validateGetNotificationsByPeriodRequest(request);
        return notificationService.getNotificationsByPeriod(request);
    }

    @POST
    @Path("notifications/getByClient")
    public Response getNotificationsByClient(GetNotificationsByClientRequest request) throws HandledServiceException {
        inputValidator.validateGetNotificationsByClientRequest(request);
        return notificationService.getNotificationsByClient(request);
    }

    @POST
    @Path("/account/create")
    public Response createAccount(CreateAccountRequest request) throws HandledServiceException {
        inputValidator.validateCreateAccountRequest(request);
        return accountService.createAccount(request);
    }

    @POST
    @Path("/account/updateAccount")
    public Response updateAccount(UpdateAccountRequest request) {
        inputValidator.validateUpdateAccountRequest(request);
        return accountService.updateAccount(request);
    }

    @POST
    @Path("/account/getUserData")
    public Response getUserData(GetUserDataRequest request) throws HandledServiceException {
        inputValidator.validateGetUserDataRequest(request);
        return accountService.getUserData(request);
    }

    @POST
    @Path("account/authorize")
    public Response authorize(AuthorizationRequest request) {
        inputValidator.validateAuthorizationRequest(request);
        return accountService.authorize(request);
    }

}
