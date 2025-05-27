package com.bivgroup.pojo.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetNotificationsByPeriodRequest extends BaseRequest {

    @NotNull
    private Date dateFrom;

    @NotNull
    private Date dateTo;
}
