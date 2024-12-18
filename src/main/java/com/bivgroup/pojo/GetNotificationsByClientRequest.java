package com.bivgroup.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNotificationsByClientRequest extends BaseRequest {

    private Long insurerId;
}
