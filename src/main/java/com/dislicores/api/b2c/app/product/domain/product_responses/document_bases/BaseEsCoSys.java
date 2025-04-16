package com.dislicores.api.b2c.app.product.domain.product_responses.document_bases;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEsCoSys{
    @JsonAlias("es-CO")
    private EsCo esCO;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class EsCo{
        private BaseSys sys;

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
}