package com.bivgroup.resource;

import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
import com.bivgroup.service.FormResponseService;
import com.bivgroup.service.NotificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

    @Inject
    NotificationService notificationService;

    @POST
    @Path("/notifications/getByContractNumber")
    public Response getNotificationsByContractNumber(GetNotificationsByContractNumberRequest request) throws Exception {
        inputValidator.validateGetNotificationsByContractNumberRequest(request);
        return notificationService.getNotificationsByContractNumber(request);
    }
}
