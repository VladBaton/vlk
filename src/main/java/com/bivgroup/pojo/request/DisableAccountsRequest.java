package com.bivgroup.pojo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisableAccountsRequest extends BaseRequest {

    private List<Long> accountIds;
}
