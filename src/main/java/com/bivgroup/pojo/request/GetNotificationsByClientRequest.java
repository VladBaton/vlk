package com.bivgroup.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNotificationsByClientRequest extends BaseRequest {

    private Long insurerId;
}
