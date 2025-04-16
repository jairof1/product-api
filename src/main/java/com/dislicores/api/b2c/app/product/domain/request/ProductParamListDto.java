package com.dislicores.api.b2c.app.product.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductParamListDto {

    @NotNull(message = "DPF000")
    @NotBlank(message = "DPF001")
    @NotEmpty(message = "DPF002")
    private String whitelabel;

    @Min(value = 0, message = "DPF003")
    private Integer size;

    @PositiveOrZero(message = "DPF003")
    @Min(value = 0, message = "DPF003")
    private Integer page;
}
