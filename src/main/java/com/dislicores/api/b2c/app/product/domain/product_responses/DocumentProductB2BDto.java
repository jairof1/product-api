package com.dislicores.api.b2c.app.product.domain.product_responses;

import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoListSys;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentProductB2BDto {

    private String entryId;
    private String environment;
    private String contentType;
    private FieldsProductB2B fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class FieldsProductB2B {
        private Map<String, String> sku;

        private BaseEsCoListSys productosRecomendados;
    }
}
