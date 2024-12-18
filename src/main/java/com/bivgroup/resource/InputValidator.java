package com.bivgroup.resource;

import com.bivgroup.pojo.Request.GetNotificationsByContractNumberRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public class InputValidator {

    public void validateGetNotificationsByContractNumberRequest(@Valid GetNotificationsByContractNumberRequest request) throws Exception {
        if (request.getContractNumber().isEmpty()) {
            throw new Exception();
        }
    }
}
