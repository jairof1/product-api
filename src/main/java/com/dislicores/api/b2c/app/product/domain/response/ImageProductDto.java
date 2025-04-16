package com.dislicores.api.b2c.app.product.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageProductDto {

    private String entryId;
    private String environment;
    private String contentType;
    private FieldsImageProduct fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString
    public class FieldsImageProduct {
        private JsonNode file;
        private JsonNode title;
    }
}
