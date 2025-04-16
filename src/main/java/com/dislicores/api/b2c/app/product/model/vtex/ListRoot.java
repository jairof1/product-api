package com.dislicores.api.b2c.app.product.model.vtex;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListRoot {

    private String id;
    private String email;
    @JsonProperty("ListItemsWrapper") private List<ListItemsWrapper> listItemsWrapper;
}