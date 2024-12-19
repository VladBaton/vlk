package com.bivgroup.mapper;

import com.bivgroup.pojo.request.BaseRequest;
import com.bivgroup.pojo.response.BaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface BaseResponseMapper {

    BaseResponseMapper INSTANCE = Mappers.getMapper(BaseResponseMapper.class);

    @Mapping(target = "rsTm",
            expression = "java((new java.text.SimpleDateFormat(com.bivgroup.constant.Constants.DATE_PATTERN_TIME)).format(new java.util.Date()))")
    @Mapping(target = "rsId", expression = "java(java.util.UUID.randomUUID().toString().replace(\"-\", \"\"))")
    BaseResponse toBaseResponse(BaseRequest request, Long statusCode, String statusDescription);

}
