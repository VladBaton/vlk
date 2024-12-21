package com.bivgroup.service;

import com.bivgroup.constant.Constants;
import com.bivgroup.entity.Contract;
import com.bivgroup.entity.Insurer;
import com.bivgroup.entity.Notification;
import com.bivgroup.mapper.EntityToPojoMapper;
import com.bivgroup.pojo.request.GetNotificationsByClientRequest;
import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
import com.bivgroup.pojo.request.GetNotificationsByPeriodRequest;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import com.bivgroup.repository.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class NotificationService {

    @Inject
    ContractRepository contractRepository;

    @Inject
    InsurerRepository insurerRepository;

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    FormResponseService formResponseService;

    public Response getNotificationsByContractNumber(GetNotificationsByContractNumberRequest request) {
        Contract contract = contractRepository.findByContractNumber(request.getContractNumber()).orElseThrow();
        List<com.bivgroup.entity.Notification> notifications = contract.getNotifications();
        if (Objects.isNull(notifications)) {
            return formResponseService
                    .formResponse(
                            formResponseService
                                    .formNotificationResponse(
                                            request,
                                            contract.getInsurer().getInsurerId(),
                                            null,
                                            0L,
                                            Constants.SUCCESSFULLY_PROCESSED_STATUS
                                    )
                    );
        }
        return formResponseService
                .formResponse(
                        formResponseService
                                .formNotificationResponse(
                                        request,
                                        contract.getInsurer().getInsurerId(),
                                        notifications.stream().map(EntityToPojoMapper.INSTANCE::toPojoNotification).collect(Collectors.toList()),
                                        0L,
                                        Constants.SUCCESSFULLY_PROCESSED_STATUS
                                )
                );
    }

    public Response getNotificationsByPeriod(GetNotificationsByPeriodRequest request) {
        List<com.bivgroup.entity.Notification> notifications =
                notificationRepository.findByPeriod(request.getDateFrom(), request.getDateTo());
        return formResponseService
                .formResponse(
                        formResponseService
                                .formNotificationResponse(
                                        request,
                                        null,
                                        notifications.stream().map(EntityToPojoMapper.INSTANCE::toPojoNotification).collect(Collectors.toList()),
                                        0L,
                                        Constants.SUCCESSFULLY_PROCESSED_STATUS
                                )
                );
    }

    public Response getNotificationsByClient(GetNotificationsByClientRequest request) {
        Insurer insurer = insurerRepository.findByInsurerId(request.getInsurerId()).orElseThrow();
        List<Notification> notifications = insurer.getNotifications();
        if (Objects.isNull(notifications)) {
            return formResponseService
                    .formResponse(
                            formResponseService
                                    .formNotificationResponse(
                                            request,
                                            insurer.getInsurerId(),
                                            null,
                                            0L,
                                            Constants.SUCCESSFULLY_PROCESSED_STATUS
                                    )
                    );
        }
        return formResponseService
                .formResponse(
                        formResponseService
                                .formNotificationResponse(
                                        request,
                                        insurer.getInsurerId(),
                                        notifications.stream().map(EntityToPojoMapper.INSTANCE::toPojoNotification).collect(Collectors.toList()),
                                        0L,
                                        Constants.SUCCESSFULLY_PROCESSED_STATUS
                                )
                );
    }

}
