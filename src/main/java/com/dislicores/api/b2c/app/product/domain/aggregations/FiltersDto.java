package com.dislicores.api.b2c.app.product.domain.aggregations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltersDto {
    // private Map<String, List<BrandBucket>> alcoholLevel;
    // private Map<String, List<BrandBucket>> bottleType;
    private FilterBrandDto brands;
    private FilterRangePriceDto rangePrice;
    private FilterCountryDto country;
    private FilterCepaDto cepa;
    private FilterMaridajeDto maridaje;
    private FilterRewardDto reward;
}
