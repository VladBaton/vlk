package com.bivgroup.filter;

import com.bivgroup.pojo.request.BaseRequest;
import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

@RequestScoped
@Getter
@Setter
public class RequestContextHolder {

    private BaseRequest request;
}
