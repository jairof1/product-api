package com.dislicores.api.b2c.app.product.domain.promotions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionResponseGenericDto {

    private String operationCenter;
    private ClientPromotionResponseDto client;
    private List<PromotionProductListResponseDto> productDtoList;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClientPromotionResponseDto {
        private String typeId;
        private String numId;
        private String clubLevel;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PromotionProductListResponseDto {

        private String sku;
        private String brand;
        private BigDecimal unitPrice;
        private BigDecimal ico;
        private BigDecimal iva;
        private DiscountDto discount;
        private PromotionTypeDto promotion;
        private boolean productWithStaggeredPromotion;
        private BigDecimal bestPriceClubMembers;
        private String bestPriceClubMembersLevel;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiscountDto {
        private BigDecimal totalDiscount;
        private BigDecimal totalPrice;
        private BigDecimal subtotalPrice;
        private BigDecimal iva;
        private BigDecimal ico;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PromotionTypeDto {
        private TypePromotionEnum type;
        private List<String> tag;
        private String promotionId;
        private float discount;
        private String skuGiftName;
    }
}
