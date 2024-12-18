package com.bivgroup.pojo.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetNotificationsByPeriodRequest extends BaseRequest {

    private Date dateFrom;
    private Date dateTo;
}
