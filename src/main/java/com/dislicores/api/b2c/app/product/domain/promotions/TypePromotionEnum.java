package com.dislicores.api.b2c.app.product.domain.promotions;

public enum TypePromotionEnum {
    MARCA,                  //porcentaje
    DESCUENTO_DIRECTO,      //porcentaje
    DESCUENTO_EN_VALOR,     //monto
    DESCUENTO_DIRECTO_ESCALONADO,      //porcentaje (promoción escalonada por productos)
    OFERTA,                 //AxB
    REGALO,                 //AxB
    DESCUENTOS_DEL_CLUB,    //porcentaje
    CONVENIOS_EMPRESARIALES, //Descuentos_empresariales
    REGALO_Y_DESCUENTO_DIRECTO, //Descuento en porcentaje mas obsequio
    SEGUNDA_UNIDAD_CON_DESCUENTO
    }
