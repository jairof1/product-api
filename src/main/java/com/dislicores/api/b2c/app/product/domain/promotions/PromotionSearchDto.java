package com.dislicores.api.b2c.app.product.domain.promotions;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PromotionSearchDto {

    private ClientDto client;

    @NotBlank(message = "DAA002")
    private String operationCenter;

    private PromotionSearchType promotionSearchType;
}