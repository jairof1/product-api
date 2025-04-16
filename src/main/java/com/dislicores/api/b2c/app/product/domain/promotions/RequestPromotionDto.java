package com.dislicores.api.b2c.app.product.domain.promotions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Normalized;

@Data
@Builder
@AllArgsConstructor @Normalized
public class RequestPromotionDto {
    private String numId;
    private String typeId;
    private String levelClub;
    private PromotionSearchType promotionSearchType;
}