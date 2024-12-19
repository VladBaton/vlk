package com.bivgroup.service;

import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.mapper.NotificationMapper;
import com.bivgroup.pojo.Notification;
import com.bivgroup.pojo.request.BaseRequest;
import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
import com.bivgroup.pojo.response.BaseResponse;
import com.bivgroup.pojo.response.NotificationResponse;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FormResponseService {

    public BaseResponse formBaseResponse(BaseRequest request, Long statusCode, String statusDescription) {
        return BaseResponseMapper.INSTANCE.toBaseResponse(request, statusCode, statusDescription);
    }

    public NotificationResponse formNotificationResponse(BaseRequest request, Long insurerId, List<Notification> notifications,
                                             Long statusCode, String statusDescription) {
        return NotificationMapper.INSTANCE.toNotificationResponse(request, insurerId, notifications, statusCode, statusDescription);
    }
}
