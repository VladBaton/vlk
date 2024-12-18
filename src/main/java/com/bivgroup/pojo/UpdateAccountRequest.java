package com.bivgroup.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountRequest extends BaseRequest {

    private String userLogin;
    private String insurerName;
    private String insurerSurname;
    private String insurerLastName;
    private String insurerEmail;
    private String insurerPhoneNumber;
}
