package com.dislicores.api.b2c.app.product.domain.aggregations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterMaridajeDto {

    private String title;
    private List<BrandBucket> items;

}
