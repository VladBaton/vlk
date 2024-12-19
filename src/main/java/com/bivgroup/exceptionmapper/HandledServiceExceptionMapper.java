package com.bivgroup.exceptionmapper;

import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.filter.RequestContextHolder;
import com.bivgroup.service.FormResponseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class HandledServiceExceptionMapper implements ExceptionMapper<HandledServiceException> {

    @Inject
    RequestContextHolder requestContextHolder;

    @Inject
    FormResponseService formResponseService;

    @Override
    public Response toResponse(HandledServiceException ex) {
        return Response
                .status(Response.Status.OK)
                .entity(
                        formResponseService
                                .formBaseResponse(requestContextHolder.getRequest(), ex.getCode(), ex.getMessage())
                )
                .build();
    }
}
