package com.dislicores.api.b2c.app.product.model.middle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(PriceListId.class)
@Table(name = "LISTA_PRECIOS")
public class PriceList implements Serializable {

    private static final long serialVersionUID = -1298186721786865302L;

    @Id
    private String code;

    @Column(name = "row_id_item", nullable = true)
    private Double rowIdItem;

    @Id
    private String sku;

    @Column(name = "precio", nullable = false)
    private Double price;

    @Column(name = "ico", nullable = true)
    private Double ico;

    @Column(name = "fecha_activacion", nullable = false)
    private Date activationDate;

    @Column(name = "iva", nullable = true)
    private Double iva;

}
