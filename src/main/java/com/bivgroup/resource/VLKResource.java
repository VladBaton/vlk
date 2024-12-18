package com.bivgroup.resource;

import com.bivgroup.pojo.GetNotificationsByContractNumberRequest;
import com.bivgroup.pojo.NotificationResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Date;
import java.util.UUID;

@Path("/vlk")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "REST методы продукта \"Виртуальный личный кабинет страхователя\"")
public class VLKResource {

    @Inject
    InputValidator inputValidator;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("/notifications/getByContractNumber")
    public Response getNotificationsByContractNumber(GetNotificationsByContractNumberRequest request) throws Exception {
        inputValidator.validateGetNotificationsByContractNumberRequest(request);
        NotificationResponse response = new NotificationResponse();
        response.setRqId(request.getRqId());
        response.setRsTm(new Date().toString());
        response.setRsId(UUID.randomUUID().toString().replace("-", ""));
        response.setInsurerId(1L);
        response.setInsurerNotifications(null);
        response.setStatusCode(0L);
        response.setStatusDescription("Обработан успешно");
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
