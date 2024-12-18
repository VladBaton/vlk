package com.bivgroup.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUserDataResponse extends BaseResponse {

    private Long insurerId;
    private String insurerName;
    private String insurerSurname;
    private String insurerLastName;
    private String insurerEmail;
    private String insurerPhoneNumber;
    private List<Contract> contracts;
}
