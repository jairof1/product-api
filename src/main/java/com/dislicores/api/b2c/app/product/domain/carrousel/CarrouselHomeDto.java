package com.dislicores.api.b2c.app.product.domain.carrousel;

import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoInteger;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoListSys;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoString;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoSys;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrouselHomeDto {
    private String entryId;
    private String environment;
    private String contentType;
    private FieldsCarrouselHome fields;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FieldsCarrouselHome{
        private BaseEsCoString titulo;
        private BaseEsCoString subtitulo;
        private BaseEsCoListSys productos;
        private BaseEsCoString fechaInicio;
        private BaseEsCoString fechaFin;
        private BaseEsCoString tipoDeEnlace;
        private BaseEsCoSys asociadoAlEnlace;
        private BaseEsCoSys banner;
        private BaseEsCoSys bannerInterno;
        private BaseEsCoInteger orden;

    }
}
