package com.bivgroup.pojo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationResponse extends BaseResponse {

    private Long insurerId;
    private String token;

    public AuthorizationResponse(Long insurerId, String token) {
        this.insurerId = insurerId;
        this.token = token;
    }
}
