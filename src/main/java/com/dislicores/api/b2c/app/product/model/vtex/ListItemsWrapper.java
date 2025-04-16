package com.dislicores.api.b2c.app.product.model.vtex;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListItemsWrapper {
    @JsonProperty("ListItems") private List<ListItems> listItems;
    @JsonProperty("IsPublic") private boolean isPublic;
    @JsonProperty("Name") private String name;
}