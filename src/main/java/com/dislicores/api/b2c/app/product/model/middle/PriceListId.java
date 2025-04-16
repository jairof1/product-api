package com.dislicores.api.b2c.app.product.model.middle;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class PriceListId implements Serializable {

    private static final long serialVersionUID = -4587602559269589215L;

    @Column(name = "lista_precio", nullable = false)
    private String code;

    @Column(name = "sku", nullable = false)
    private String sku;

    public PriceListId(String code, String sku) {
        this.code = code;
        this.sku = sku;
    }

}
