package com.bivgroup.resource;

import com.bivgroup.pojo.Request.GetNotificationsByContractNumberRequest;
import com.bivgroup.service.FormResponseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/vlk")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "REST методы продукта \"Виртуальный личный кабинет страхователя\"")
public class VLKResource {

    @Inject
    InputValidator inputValidator;

    @Inject
    FormResponseService formResponseService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("/notifications/getByContractNumber")
    public Response getNotificationsByContractNumber(GetNotificationsByContractNumberRequest request) throws Exception {
        inputValidator.validateGetNotificationsByContractNumberRequest(request);
        return Response
                .status(Response.Status.OK)
                .entity(
                        formResponseService.formResponse(request, 228L, null, 1L, "Обработан успешно")
                ).build();
    }
}
