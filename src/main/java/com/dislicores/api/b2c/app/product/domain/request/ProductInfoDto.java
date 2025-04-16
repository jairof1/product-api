package com.dislicores.api.b2c.app.product.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de productos")
public class ProductInfoDto {

    @Valid
    @NotNull(message = "DPF000")
    @Size(min = 1, message = "DPF001")
    private List<String> skuIds;
}
