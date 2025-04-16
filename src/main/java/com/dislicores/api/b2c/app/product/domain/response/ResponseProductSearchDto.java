package com.dislicores.api.b2c.app.product.domain.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProductSearchDto {

    private String _index;
    private String _type;
    private String _id;
    private JsonNode _score;
    private ResponseIndexB2CDto _source;
    private JsonNode _sort;

}
