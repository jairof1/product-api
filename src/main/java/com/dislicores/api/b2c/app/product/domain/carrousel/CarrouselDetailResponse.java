package com.dislicores.api.b2c.app.product.domain.carrousel;

import com.dislicores.api.b2c.app.product.domain.product_responses.ProductList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrouselDetailResponse {
    private String id;
    private String title;
    private String subtitle;
    private String linkType;
    private String link;
    private List<ProductList> products;
    private int order;
}
