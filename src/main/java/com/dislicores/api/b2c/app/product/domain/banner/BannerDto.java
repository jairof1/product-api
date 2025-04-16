package com.dislicores.api.b2c.app.product.domain.banner;

import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BannerDto {

    private BaseEsCoListSys productosBanner;
    private BaseEsCoBoolean mostrarEnAppB2c;
    private BaseEsCoString titulo;
    private BaseEsCoSys banner;
    private BaseEsCoBoolean mostrarEnHome;
    private BaseEsCoListSys categoriaBanner;

}
