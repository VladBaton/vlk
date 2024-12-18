package com.bivgroup.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "Запрос уведомлений по номеру контракта")
public class GetNotificationsByContractNumberRequest extends BaseRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 16)
    @Schema(description = "Номер контракта")
    private String contractNumber;
}
