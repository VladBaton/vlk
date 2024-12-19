package com.bivgroup.mapper;

import com.bivgroup.pojo.Notification;
import com.bivgroup.pojo.request.BaseRequest;
import com.bivgroup.pojo.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(target = "rsTm",
            expression = "java((new java.text.SimpleDateFormat(com.bivgroup.constant.Constants.DATE_PATTERN_TIME)).format(new java.util.Date()))")
    @Mapping(target = "rsId", expression = "java(java.util.UUID.randomUUID().toString().replace(\"-\", \"\"))")
    NotificationResponse toNotificationResponse(BaseRequest request, Long insurerId, List<Notification> insurerNotifications, Long statusCode, String statusDescription);
}
