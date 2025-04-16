package com.dislicores.api.b2c.app.product.domain.middle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaPrecioInventarioObjectDto {

    private String id;
    private String bodega;
    // private Double rowId;
    //private Double weightSku;
    //private String mesaureUnit;
    private Double inventary;
    private String listPrice;
    private String skuId;
    private Double price;
    private Double ico;
    private Double iva;

}
