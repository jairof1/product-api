package com.dislicores.api.b2c.app.product.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDto {

    private String entryId;
    private String environment;
    private String contentType;
    private FieldsSubCategory fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class FieldsSubCategory{
        private JsonNode nombre;
        private JsonNode categoriaApp;
        private JsonNode plataforma;
        private List<SubCategoryDto> subCategoryDtoList;
    }


}
