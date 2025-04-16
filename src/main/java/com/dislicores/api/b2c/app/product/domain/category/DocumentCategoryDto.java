package com.dislicores.api.b2c.app.product.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCategoryDto {

    private List<SubCategoryDto> categoriesDocument;
}
