package com.bivgroup.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAccountRequest extends BaseRequest {

    @NotNull
    @NotBlank
    private String login;
}
