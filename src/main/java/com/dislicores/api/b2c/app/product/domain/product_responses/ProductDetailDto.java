package com.dislicores.api.b2c.app.product.domain.product_responses;

import com.dislicores.api.b2c.app.product.domain.response.SpecificationsDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {

    private Double alcoholLevel;
    private String category;
    private List<String> categoryFullPath;
    private String countryOfOrigin;
    private String countryOfOriginImage;
    private String description; //TODO: eliminar en futuro paso a produccíon
    private JsonNode descriptionB2C;
    private Double discountPercent; // TODO
    private Double price;
    private BigDecimal clubLevelPrice;
    private String clubLevelPriceLevel;
    private String productFullName;
    private String idProduct;
    private List<String> productImages;
    private Double score;
    private String skuId;
    private Boolean isFavorite;
    private ProductList.InfoPriceDto infoPrice;
    private String slug;

    private SpecificationsDto specifications;

    private int stock;
    private List<String> tags;  // TODO
    private String type; //BottleType

    private List<String> wineVariety;  // TODO


    private Double iva;
    private Double ico;
    private List<String> customTags;
}
