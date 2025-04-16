package com.dislicores.api.b2c.app.product.domain.whitelabel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhiteLabelActiveDto {
    private String entryId;
    private String environment;
    private String contentType;
    private FieldsWhitelabel fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString
    public static class FieldsWhitelabel {
        private JsonNode wirelabelReference;
        private JsonNode activeWhitelabel;
        private JsonNode whitelabelName;
        private JsonNode listPriceReference;
        private JsonNode operationalCenterReference;
        private JsonNode apiVtex;
    }

    @Data
    @NoArgsConstructor
    public static class ApiVtex {
        private String key;
        private String token;
    }
}

