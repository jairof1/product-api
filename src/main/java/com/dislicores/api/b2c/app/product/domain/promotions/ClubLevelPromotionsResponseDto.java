package com.dislicores.api.b2c.app.product.domain.promotions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubLevelPromotionsResponseDto {

    private String clubCategory;
    private List<PromotionClubLevelDto> promotions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PromotionClubLevelDto {
        private String type;
        private String tag;
        private String promotionId;
        private String discount;
        private String applyTo;
        private String value;
        private String clubLevel;
    }

}
