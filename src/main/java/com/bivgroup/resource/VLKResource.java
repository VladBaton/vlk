package com.bivgroup.resource;

import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
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

    @POST
    @Path("/notifications/getByContractNumber")
    public Response getNotificationsByContractNumber(GetNotificationsByContractNumberRequest request) throws Exception {
        inputValidator.validateGetNotificationsByContractNumberRequest(request);
        return Response
                .status(Response.Status.OK)
                .entity(
                        formResponseService.formNotificationResponse(request, 228L, null, 0L, "Обработан успешно")
                ).build();
    }
}
