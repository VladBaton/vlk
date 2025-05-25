package com.bivgroup.filter;

import com.bivgroup.constant.Constants;
import com.bivgroup.pojo.request.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Provider
public class RequestLoggingFilter implements ContainerRequestFilter {

    @Inject
    RequestContextHolder requestContextHolder;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(requestContext.getEntityStream()))) {
            String line;
            while (Objects.nonNull((line = reader.readLine()))) {
                requestBody.append(line);
            }
        }
        restoreEntityStream(requestBody.toString(), requestContext);
        requestContextHolder.setRequest(createBaseRequest(getResultMap(requestBody.toString())));
    }

    private void restoreEntityStream(String requestBody, ContainerRequestContext requestContext) {
        InputStream inputStream = new ByteArrayInputStream(requestBody.getBytes());
        requestContext.setEntityStream(inputStream);
    }

    private Map<String, String> getResultMap(String requestBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(requestBody);
        Map<String, String> resultMap = new HashMap<>();

        Optional<JsonNode> rqIdNodeOptional = Optional.ofNullable(jsonNode.get(Constants.FieldName.RQ_ID));
        Optional<JsonNode> rqTmNodeOptional = Optional.ofNullable(jsonNode.get(Constants.FieldName.RQ_TM));
        rqIdNodeOptional.ifPresent(rqIdNode -> resultMap.put(Constants.FieldName.RQ_ID, rqIdNode.asText("")));
        rqTmNodeOptional.ifPresent(rqTmNode -> resultMap.put(Constants.FieldName.RQ_TM, rqTmNode.asText("")));

        return resultMap;
    }

    private BaseRequest createBaseRequest(Map<String, String> argumentsMap) {
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setRqId(argumentsMap.get(Constants.FieldName.RQ_ID));
        baseRequest.setRqTm(argumentsMap.get(Constants.FieldName.RQ_TM));
        return baseRequest;
    }
}
