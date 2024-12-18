package com.bivgroup.pojo.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest extends BaseRequest {

    private String contractNumber;
    private String password;
}
