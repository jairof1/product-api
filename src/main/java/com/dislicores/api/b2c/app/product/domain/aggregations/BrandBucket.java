package com.dislicores.api.b2c.app.product.domain.aggregations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandBucket {
    private String key;
    private Integer doc_count;
}