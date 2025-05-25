package com.bivgroup.exceptionmapper;

import com.bivgroup.mapper.BaseResponseMapper;
import com.bivgroup.pojo.request.BaseRequest;
import com.bivgroup.pojo.response.BaseResponse;
import com.bivgroup.service.FormResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    @Override
    public Response toResponse(JsonProcessingException ex) {
        return Response
                .status(422, "Unprocessable entity")
                .entity(BaseResponseMapper.INSTANCE.toBaseResponse(new BaseRequest(), 422L, "Ошибка десериализации"))
                .build();
    }
}
