package com.dislicores.api.b2c.app.product.domain.promotions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String typeId;
    private String numId;
    private String clubLevel;
}