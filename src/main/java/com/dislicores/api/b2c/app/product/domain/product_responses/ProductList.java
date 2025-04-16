package com.dislicores.api.b2c.app.product.domain.product_responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductList {

    private String name;
    private String idProduct;
    private String idSku;
    private String idEntryId;
    private String bottleType;
    private Boolean isFavorite;
    private Boolean isDigital;
    private List<String> cepa;
    private List<String> images;
    private Double maximoAVender;
    private Double price;
    private Double iva;
    private Double ico;
    private Double stock;
    private List<String> tags;
    private List<Map<String, String>> categoryPath;
    private Double discountPercent;
    private List<String> customTags;
    private InfoPriceDto infoPrice;
    private boolean productWithStaggeredPromotion;
    private BigDecimal bestPriceClubMembers;
    private String bestPriceClubMembersLevel;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InfoPriceDto {

        private BigDecimal subTotalPrice;
        private BigDecimal iva;
        private BigDecimal ico;
        private BigDecimal totalDiscount;
        private BigDecimal totalPrice;
        private String promotionId;
        private String promotionType;
    }

}
