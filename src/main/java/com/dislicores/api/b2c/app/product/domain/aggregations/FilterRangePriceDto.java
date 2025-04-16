package com.dislicores.api.b2c.app.product.domain.aggregations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRangePriceDto {

    private String title;
    private List<Map<String, Integer>> items;

}
