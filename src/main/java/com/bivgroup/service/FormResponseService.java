package com.bivgroup.service;

import com.bivgroup.mapper.NotificationMapper;
import com.bivgroup.pojo.Notification;
import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
import com.bivgroup.pojo.response.NotificationResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FormResponseService {

    public NotificationResponse formResponse(GetNotificationsByContractNumberRequest request, Long insurerId, List<Notification> notifications,
                                 Long statusCode, String statusDescription) {
        NotificationResponse response = NotificationMapper.INSTANCE.toNotificationResponse(request, notifications, statusCode, statusDescription);
        response.setInsurerId(0L);
        return response;
    }
}
