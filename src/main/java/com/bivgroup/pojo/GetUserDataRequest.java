package com.bivgroup.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDataRequest extends BaseRequest {

    private Long insurerId;
}
