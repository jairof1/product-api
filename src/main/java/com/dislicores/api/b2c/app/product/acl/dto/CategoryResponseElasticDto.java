package com.dislicores.api.b2c.app.product.acl.dto;

import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoString;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CategoryResponseElasticDto {
    @JsonAlias("fechaDeInicio")
    private BaseEsCoDate initialDate;
    @JsonAlias("fechaDeFin")
    private BaseEsCoDate finalDate;
    @JsonAlias("ordenarProductos")
    private EsCoStringList producto;
    private List<String> sku;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EsCoStringList {
        @JsonAlias("es-CO")
        private List<JsonNode> esCO;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class BaseEsCoDate {
        @JsonAlias("es-CO")
        private LocalDate esCO;
    }
}
