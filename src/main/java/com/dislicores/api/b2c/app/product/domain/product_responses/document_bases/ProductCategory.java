package com.dislicores.api.b2c.app.product.domain.product_responses.document_bases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategory{
    private String nameCat;
    private String nameSubCat;
    private String EntryId;
}
