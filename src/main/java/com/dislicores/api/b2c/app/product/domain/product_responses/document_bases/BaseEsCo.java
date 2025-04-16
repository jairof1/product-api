package com.dislicores.api.b2c.app.product.domain.product_responses.document_bases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEsCo {
    private BaseSys sys;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class BaseSys{
        private String linkType;
        private String id;
        private String type;
    }
}
