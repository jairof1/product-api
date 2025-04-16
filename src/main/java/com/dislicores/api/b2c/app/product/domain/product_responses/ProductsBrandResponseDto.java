package com.dislicores.api.b2c.app.product.domain.product_responses;

import com.dislicores.api.b2c.app.product.domain.aggregations.FiltersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsBrandResponseDto {
    private String banner;
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalElements;
    private List<ProductList> productsFiltered;
    private FiltersDto filters;
}
