package com.dislicores.api.b2c.app.product.domain.firebase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseCartProductDto {
    private Double price;
    private int ico;
    private int iva;
    private String discountPercent;
    private Double subTotal;
    private int discount;
    private Double totalPrice;
    private String skuId;
    private String productId;
    private String productFullName;
    private int stock;
    private String imageUrlProduct;
    private String type;
    private String category;
    private String imageUrlCategory;
    private List<String> tags;
    private boolean favorite;
    private int count;
    private InfoPriceDto infoPrice;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InfoPriceDto {
        private Double subTotalPrice;
        private Double iva;
        private Double ico;
        private Double totalDiscount;
        private Double totalPrice;
        private String promotionId;
        private String promotionType;
    }

}
