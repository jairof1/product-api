package com.dislicores.api.b2c.app.product.model.vtex;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListItems {

    @JsonProperty("Id") private int id;
    @JsonProperty("ProductId") private String productId;
    @JsonProperty("Sku") private String sku;
    @JsonProperty("Title") private String title;
}