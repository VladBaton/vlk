package com.bivgroup.pojo.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNotificationsByClientRequest extends BaseRequest {

    private Long insurerId;
}
