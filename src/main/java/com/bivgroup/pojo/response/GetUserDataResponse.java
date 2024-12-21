package com.bivgroup.pojo.response;

import com.bivgroup.pojo.Contract;
import com.bivgroup.pojo.Insurer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetUserDataResponse extends BaseResponse {

    Insurer insurer;
}
