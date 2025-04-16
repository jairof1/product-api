package com.dislicores.api.b2c.app.product.domain.response;

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
public class ResponseSearchDto {

    private String took;
    private String timed_out;
    private JsonNode _shards;
    private JsonNode aggregations;
    private HitsReceived hits;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class HitsReceived {

        private TotalReceived total;
        private JsonNode max_score;
        private List<ResponseProductSearchDto> hits;


        @Getter
        @Setter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class TotalReceived{

            private Integer value;
            private String relation;

        }

    }

}
