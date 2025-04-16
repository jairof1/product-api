package com.dislicores.api.b2c.app.product.domain.carrousel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OriginSection {
    HOME(10),
    CART(1),
    CART_BAG(1);

    private final int maxElementsToReturn;
}
