package com.bivgroup.mapper;

import com.bivgroup.pojo.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface EntityToPojoMapper {

    EntityToPojoMapper INSTANCE = Mappers.getMapper(EntityToPojoMapper.class);


    @Mapping(target = "contractId", expression = "java(notification.getContract().getContractId())")
    @Mapping(target = "insurerId", expression = "java(notification.getInsurer().getInsurerId())")
    Notification toPojoNotification(com.bivgroup.entity.Notification notification);
}
