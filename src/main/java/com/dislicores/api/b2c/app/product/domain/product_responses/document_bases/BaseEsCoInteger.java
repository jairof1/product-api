package com.dislicores.api.b2c.app.product.domain.product_responses.document_bases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEsCoInteger {
    @JsonProperty("es-CO")
    private Integer esCO;
}