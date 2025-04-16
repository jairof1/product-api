package com.dislicores.api.b2c.app.product.domain.aggregations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggresgationsDto {

    private BrandsDetected brandsDetected;
    private AlcoholLevelDetected alcoholLevelDetected;
    private PriceBucket maxPrice;
    private PriceBucket minPrice;
    private CountryDetected countryDetected;
    private CepaDetected cepaDetected;
    private MaridajeDetected maridajeDetected;
    private RewardDetected rewardDetected;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BrandsDetected {
        private Integer doc_count_error_upper_bound;
        private Integer sum_other_doc_count;
        private List<BrandBucket> buckets;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AlcoholLevelDetected {
        private Integer doc_count_error_upper_bound;
        private Integer sum_other_doc_count;
        private List<BrandBucket> buckets;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountryDetected {
        private Integer doc_count_error_upper_bound;
        private Integer sum_other_doc_count;
        private List<BrandBucket> buckets;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CepaDetected {
        private Integer doc_count_error_upper_bound;
        private Integer sum_other_doc_count;
        private List<BrandBucket> buckets;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MaridajeDetected {
        private Integer doc_count_error_upper_bound;
        private Integer sum_other_doc_count;
        private List<BrandBucket> buckets;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RewardDetected {
        private Integer doc_count_error_upper_bound;
        private Integer sum_other_doc_count;
        private List<BrandBucket> buckets;
    }


}
