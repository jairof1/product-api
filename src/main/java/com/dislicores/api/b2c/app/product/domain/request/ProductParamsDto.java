package com.dislicores.api.b2c.app.product.domain.request;

import com.dislicores.api.b2c.app.product.domain.promotions.DocumentTypeEnum;
import com.dislicores.api.b2c.app.product.domain.promotions.LevelClubEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductParamsDto {

    @NotNull(message = "DPF000")
    @NotBlank(message = "DPF001")
    @NotEmpty(message = "DPF002")
    private String whitelabel;

    private String subcategory;

    @PositiveOrZero(message = "DPF003")
    @Min(value = 0, message = "DPF003")
    private Integer minRangePrice;

    @PositiveOrZero(message = "DPF003")
    @Min(value = 0, message = "DPF003")
    private Integer maxRangePrice;

    private String countryOriginCode;

    private String pairing;

    private String brand;

    private String cepa;

    private LevelClubEnum levelClub;

    private DocumentTypeEnum documentType;

    private String documentNumber;

    private String companion;

    @Builder.Default
    private Integer size = 10;

    @Builder.Default
    @PositiveOrZero(message = "DPF003")
    @Min(value = 0, message = "DPF003")
    private Integer page = 0;

    @Builder.Default
    private String orderBy = "price";

    @Builder.Default
    private String orderFor = "desc";

    private String search;

}
