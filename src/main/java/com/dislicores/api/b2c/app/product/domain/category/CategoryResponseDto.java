package com.dislicores.api.b2c.app.product.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponseDto {
    private String queryCategory;
}
