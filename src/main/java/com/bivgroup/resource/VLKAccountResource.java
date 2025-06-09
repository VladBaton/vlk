package com.bivgroup.resource;

import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.*;
import com.bivgroup.pojo.response.AuthorizationResponse;
import com.bivgroup.service.AccountService;
import com.bivgroup.service.FormResponseService;
import com.bivgroup.service.VLKJwtService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/account")
public class VLKAccountResource {

    @Inject
    VLKJwtService vlkJwtService;

    @Inject
    InputValidator inputValidator;

    @Inject
    AccountService accountService;

    @Inject
    FormResponseService formResponseService;

    @POST
    @Path("authorize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @PermitAll
    public Response authorize(AuthorizationRequest request) throws HandledServiceException {
        inputValidator.validateAuthorizationRequest(request);
        AuthorizationResponse response = vlkJwtService.generateJwt(request);
        return Response.ok(response).build();
    }

    @POST
    @Path("createVerificationCode")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @PermitAll
    public Response createVerificationCode(CreateVerificationCodeRequest request) throws HandledServiceException {
        inputValidator.validateCreateVerificationCodeRequest(request);
        return accountService.createVerificationCode(request);
    }

    @POST
    @Path("validateVerificationCode")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @PermitAll
    public Response validateVerificationCode(ValidateVerificationCodeRequest request) throws HandledServiceException {
        return accountService.validateVerificationCode(request);
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @PermitAll
    public Response createAccount(CreateAccountRequest request) throws HandledServiceException {
        inputValidator.validateCreateAccountRequest(request);
        return accountService.createAccount(request);
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "admin"})
    public Response deleteAccount(DeleteAccountRequest request) throws HandledServiceException {
        inputValidator.validateDeleteAccountRequest(request);
        return accountService.deleteAccount(request);
    }

    @POST
    @Path("disable")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"admin"})
    public Response disableAccounts(DisableAccountsRequest request) {
        return accountService.disableAccounts(request);
    }

    @POST
    @Path("enable")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"admin"})
    public Response enableAccounts(EnableAccountsRequest request) {
        return accountService.enableAccounts(request);
    }
}
