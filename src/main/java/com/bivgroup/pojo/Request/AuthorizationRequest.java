package com.bivgroup.pojo.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationRequest extends BaseRequest {

    private String userLogin;
    private String password;
}
