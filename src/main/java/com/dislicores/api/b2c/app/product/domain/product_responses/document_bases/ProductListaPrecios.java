package com.dislicores.api.b2c.app.product.domain.product_responses.document_bases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductListaPrecios{
    private String id;
    private String bodega;
    private Double inventary;
    private String listPrice;
    private String skuId;
    private Double price;
    private Double ico;
    private Double iva;
    private Boolean withStock;
}
