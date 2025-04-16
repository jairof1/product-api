package com.dislicores.api.b2c.app.product.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificationsDto {

    private Integer tanicity;           // check -> b2cTanicidad
    private Integer body;               // check -> b2cCuerpo
    private Integer dry;                // check -> b2cSeco
    private JsonNode flavorNotes;         // check -> notasDeCata
    private List<String> pairing;       // check -> acompanante

}
