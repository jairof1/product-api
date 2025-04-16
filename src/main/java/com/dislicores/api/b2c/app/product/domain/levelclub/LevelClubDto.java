package com.dislicores.api.b2c.app.product.domain.levelclub;

import com.dislicores.api.b2c.app.product.domain.product_responses.DocumentProductDto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LevelClubDto {

    private String entryId;
    private String environment;
    private String contentType;
    private FieldsLevelClub fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class FieldsLevelClub{
        private JsonNode priceTables;
        private Map<String, List<Map<String, Map<String, String>>>> recomendados;
        private JsonNode tag;
        private JsonNode nombre;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Sys{
        private String linkType;
        private String id;
        private String type;
    }


/*    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class RecomendadosEsCo{
        @JsonAlias("es-CO")
        private EsCo esCO;

        @Getter
        @Setter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class EsCo{
            private List<JsonNode> sys;

            @Getter
            @Setter
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public class BaseSys{
                private String linkType;
                private String id;
                private String type;
            }
        }
    }*/

}

