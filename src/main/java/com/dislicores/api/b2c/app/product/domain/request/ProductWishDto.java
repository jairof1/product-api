package com.dislicores.api.b2c.app.product.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWishDto {

    @NotNull(message = "DPF000")
    @NotBlank(message = "DPF001")
    @NotEmpty(message = "DPF002")
    private String sku;
}