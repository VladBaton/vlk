package com.bivgroup.resource;

import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.AuthorizationRequest;
import com.bivgroup.pojo.request.CreateAccountRequest;
import com.bivgroup.pojo.response.AuthorizationResponse;
import com.bivgroup.service.AccountService;
import com.bivgroup.service.VLKJwtService;
import jakarta.annotation.security.PermitAll;
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

    @POST
    @Path("authorize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response authorize(AuthorizationRequest request) throws HandledServiceException {
        inputValidator.validateAuthorizationRequest(request);
        AuthorizationResponse response = vlkJwtService.generateJwt(request);
        return Response.ok(response).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createAccount(CreateAccountRequest request) throws HandledServiceException {
        inputValidator.validateCreateAccountRequest(request);
        return accountService.createAccount(request);
    }
}
