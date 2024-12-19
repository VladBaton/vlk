package com.bivgroup.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationRequest extends BaseRequest {

    private String userLogin;
    private String password;
}
