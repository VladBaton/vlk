package com.bivgroup.service;

import com.bivgroup.pojo.Notification;
import com.bivgroup.pojo.Request.GetNotificationsByContractNumberRequest;
import com.bivgroup.pojo.Response.NotificationResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FormResponseService {

    public NotificationResponse formResponse(GetNotificationsByContractNumberRequest request, Long insurerId, List<Notification> notifications,
                                 Long statusCode, String statusDescription) {
        NotificationResponse response = new NotificationResponse();
        response.setRqId(request.getRqId());
        response.setRsTm(new Date().toString());
        response.setRsId(UUID.randomUUID().toString().replace("-", ""));
        response.setInsurerId(1L);
        response.setInsurerNotifications(notifications);
        response.setStatusCode(statusCode);
        response.setStatusDescription(statusDescription);
        return response;
    }
}
