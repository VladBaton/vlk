package com.bivgroup.resource;

import com.bivgroup.service.VLKJwtService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/authorization")
@Produces(MediaType.TEXT_PLAIN)
public class VLKJwtResource {

    @Inject
    VLKJwtService vlkJwtService;

    @GET
    public Response getJwt() {
        String jwt = vlkJwtService.generateJwt();
        return Response.ok(jwt).build();
    }
}
