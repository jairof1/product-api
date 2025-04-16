package com.dislicores.api.b2c.app.product.domain.response;


import com.dislicores.api.b2c.app.product.domain.middle.ListaPrecioInventarioObjectDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseIndexB2CDto {

    private String entryId;
    private String environment;
    private String contentType;
    private ProductFields fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class ProductFields {

        private JsonNode nivelDeAlcohol;
        private JsonNode b2cDescripcion; //TODO: eliminar en futuro paso a produccíon
        private JsonNode descripcion;
        private JsonNode subcategoria;
        private JsonNode nombre;
        private JsonNode digital;
        private JsonNode B2cIdProducto;
        private JsonNode sku;
        private JsonNode b2cTipoDeBotella;
        private JsonNode b2cCepa;
        private List<Map<String, String>> categoryPath;

        private JsonNode imagenes;
        private ImageProductDto imageProductDto;
        private JsonNode paisRef;
        private JsonNode maximoAVender;
        private CountryDto countryDto;
        private SpecificationsDto specifications;
        private List<ListaPrecioInventarioObjectDto> listaPrecioInventarioObjectDtos5;

        private JsonNode etiquetasPersonalizadasB2c;
    }

}
