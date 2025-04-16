package com.dislicores.api.b2c.app.product.domain.middle;

import org.springframework.beans.factory.annotation.Value;

public interface ListaPrecioInventarioDto {

    @Value("#{target.bodega}")
    String getBodega();

    //@Value("#{target.row_id}")
    //Double getRowId();

    //@Value("#{target.peso_sku}")
    //Double getWeightSku();

    //@Value("#{target.unidad}")
    //String getMeasureUnit();

    @Value("#{target.inventario}")
    Integer getInventary();

    @Value("#{target.lista_precio}")
    String getListPrice();

    @Value("#{target.sku}")
    String getSkuId();

    @Value("#{target.precio}")
    Double getPrice();

    @Value("#{target.ico}")
    Double getIco();

    @Value("#{target.iva}")
    Double getIva();

}
