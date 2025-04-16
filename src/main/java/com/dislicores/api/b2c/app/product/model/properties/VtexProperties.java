package com.dislicores.api.b2c.app.product.model.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "vtex")
public class VtexProperties {
    private String endpointGetWishlist;
    private String endpointSaveWishlist;
    private String endpointDeleteWishlist;
    private String vtexApiKey;
    private String vtexApiToken;

    private String endpointGetProductSearch;
}