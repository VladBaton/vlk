package com.bivgroup.exceptionmapper;

import com.bivgroup.filter.RequestContextHolder;
import com.bivgroup.service.FormResponseService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    FormResponseService formResponseService;

    @Inject
    RequestContextHolder requestContextHolder;

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        return Response
                .status(Response.Status.OK)
                .entity(
                        formResponseService
                                .formBaseResponse(requestContextHolder.getRequest(),
                                        2L,
                                        String.format("Ошибки валидации: %s",
                                                ex.getConstraintViolations()
                                                        .stream()
                                                        .map(
                                                                constraintViolation ->
                                                                        constraintViolation
                                                                                .getPropertyPath()
                                                                                + ": "
                                                                                + constraintViolation.getMessage()
                                                        )
                                                        .toList()
                                        )
                                )
                )
                .build();
    }
}
