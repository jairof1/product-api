package com.dislicores.api.b2c.app.product.model.search;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Document(indexName = "b2c_new")
public class IndexB2CNewSearch implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String entryId;
    private String environment;
    private String contentType;
    private ObjectNode fields;

}