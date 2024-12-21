package com.bivgroup.service;

import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.mapper.NotificationMapper;
import com.bivgroup.pojo.Insurer;
import com.bivgroup.pojo.Notification;
import com.bivgroup.pojo.request.BaseRequest;
import com.bivgroup.pojo.request.GetUserDataRequest;
import com.bivgroup.pojo.response.BaseResponse;
import com.bivgroup.pojo.response.GetUserDataResponse;
import com.bivgroup.pojo.response.NotificationResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class FormResponseService {

    public BaseResponse formBaseResponse(BaseRequest request, Long statusCode, String statusDescription) {
        return BaseResponseMapper.INSTANCE.toBaseResponse(request, statusCode, statusDescription);
    }

    public NotificationResponse formNotificationResponse(BaseRequest request, Long insurerId, List<Notification> notifications,
                                                         Long statusCode, String statusDescription) {
        return NotificationMapper.INSTANCE.toNotificationResponse(request, insurerId, notifications, statusCode, statusDescription);
    }

    public Response formResponse(BaseResponse response) {
        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }
}
