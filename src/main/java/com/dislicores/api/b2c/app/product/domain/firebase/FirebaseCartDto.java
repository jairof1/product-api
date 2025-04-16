package com.dislicores.api.b2c.app.product.domain.firebase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseCartDto {

    // private LocalDate date;
    private String id;

    private String email;

    private String products;

    private int notification;

    private List<FirebaseCartProductDto> productsList;
}
