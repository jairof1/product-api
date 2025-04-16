package com.dislicores.api.b2c.app.product.domain.aggregations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceBucket {

    private Integer doc_count;
    private Map<String, Integer> maxPriceWhitelabel;
    private Map<String, Integer> minPriceWhitelabel;

}
