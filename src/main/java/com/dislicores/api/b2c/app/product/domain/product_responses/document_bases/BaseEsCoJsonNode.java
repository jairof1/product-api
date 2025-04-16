package com.dislicores.api.b2c.app.product.domain.product_responses.document_bases;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEsCoJsonNode{
    @JsonAlias("es-CO")
    private JsonNode esCO;
}