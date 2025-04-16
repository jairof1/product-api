package com.dislicores.api.b2c.app.product.domain.carrousel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrouselResponse {
    private String id;
    private String title;
    private String subtitle;
    private String linkType;
    private String link;
    private List<String> products;
    private int order;
    private String bannerImageUrl;
    private String internalBannerImageUrl;
}
