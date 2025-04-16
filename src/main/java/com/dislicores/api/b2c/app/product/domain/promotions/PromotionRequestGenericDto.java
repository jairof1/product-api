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
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionRequestGenericDto {

    private String operationCenter;
    private ClientPromotionRequestDto client;
    private List<PromotionProductListRequestDto> productDtoList;


    @Data @NoArgsConstructor @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClientPromotionRequestDto {
        private String typeId;
        private String numId;
        private String clubLevel;
    }


    @Data @NoArgsConstructor @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PromotionProductListRequestDto {

        private String sku;
        private String brand;
        private BigDecimal unitPrice;
        private BigDecimal ico;
        private BigDecimal iva;

    }

}
