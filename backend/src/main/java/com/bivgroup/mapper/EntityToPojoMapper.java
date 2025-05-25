package com.bivgroup.mapper;

import com.bivgroup.entity.Insurer;
import com.bivgroup.pojo.Contract;
import com.bivgroup.pojo.Notification;
import com.bivgroup.pojo.Payment;
import com.bivgroup.pojo.response.GetUserDataResponse;
import com.bivgroup.service.HandbookService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface EntityToPojoMapper {

    EntityToPojoMapper INSTANCE = Mappers.getMapper(EntityToPojoMapper.class);


    @Mapping(target = "contractId", expression = "java(notification.getContract().getContractId())")
    @Mapping(target = "insurerId", expression = "java(notification.getContract().getInsurer().getInsurerId())")
    Notification toPojoNotification(com.bivgroup.entity.Notification notification);


    Payment toPaymentPojo(com.bivgroup.entity.Payment payment);

    @Mapping(target = "payments", expression = "java(" +
            "contract.getPayments().stream().map(payment -> EntityToPojoMapper.INSTANCE.toPaymentPojo(payment)).collect(java.util.stream.Collectors.toList())" +
            ")")
    @Mapping(target = "contractExt", expression = "java(handbookService.getContractExtensionMap(contract))")
    Contract toContractPojo(com.bivgroup.entity.Contract contract, @Context HandbookService handbookService);

    @Mapping(target = "insurerName", expression = "java(insurer.getName())")
    @Mapping(target = "insurerSurname", expression = "java(insurer.getSurname())")
    @Mapping(target = "insurerLastName", expression = "java(insurer.getLastName())")
    @Mapping(target = "insurerEmail", expression = "java(insurer.getEmail())")
    @Mapping(target = "insurerPhoneNumber", expression = "java(insurer.getPhone())")
    @Mapping(target = "contracts", expression = "java(" +
            "insurer.getContracts().stream().map(contract -> EntityToPojoMapper.INSTANCE.toContractPojo(contract, handbookService)).collect(java.util.stream.Collectors.toList())" +
            ")")
    com.bivgroup.pojo.Insurer toInsurerPojo(Insurer insurer, @Context HandbookService handbookService);
}
