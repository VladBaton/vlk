package com.bivgroup.pojo.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDataRequest extends BaseRequest {

    private Long insurerId;
}
