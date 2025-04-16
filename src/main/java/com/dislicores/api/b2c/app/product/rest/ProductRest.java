package com.dislicores.api.b2c.app.product.rest;

import com.dislicores.api.b2c.app.product.business.ProductBusiness;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductsFilteredResponseDto;
import com.dislicores.api.b2c.app.product.domain.promotions.DocumentTypeEnum;
import com.dislicores.api.b2c.app.product.domain.promotions.LevelClubEnum;
import com.dislicores.api.b2c.app.product.domain.request.ProductParamsDto;
import com.dislicores.api.b2c.app.product.domain.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@Tag(name = "Controlador de productos", description = "Servicio dedicado al manejo de productos")
public class ProductRest {

    private final ProductBusiness productBusiness;

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener listado de productos", description = "Retorna un listado de productos segun whitelabel (mandatorio) y filtros opcionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos devuelto correctamente", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud con parametros incorrectos", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Productos no encontrados", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error inesperado durante el proceso", content = @Content(schema = @Schema(implementation = ResponseDto.class)))})
    public ResponseDto<ProductsFilteredResponseDto> getProducts(
            @Parameter(name = "Authorization", description = "Token del usuario autenticado para extraer datos del usuario", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "Authorization", defaultValue = "") String token,
            @Parameter(name = "Ip-Address", description = "Dirección IP desde la cual se realiza la petición", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "Ip-Address") @NotBlank(message = "DPA002") String ipAddress,
            @Parameter(name = "Whitelabel code", description = "Código del whitelabel", required = true, in = ParameterIn.QUERY) @RequestParam(required = true, value = "whitelabel") String whitelabel,
            @Parameter(name = "sucategoria", description = "filtrar por categoria o subcategoria", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "subcategory") String subcategory,
            @Parameter(name = "Max price range", description = "Rango de precio superior", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "maxRangePrice") Integer maxRangePrice,
            @Parameter(name = "Min price range", description = "Rango de precio inferior", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "minRangePrice") Integer minRangePrice,
            @Parameter(name = "Country Origin Code", description = "Filtro por pais de origen solo para vinos", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "countryOriginCode") String countryOriginCode,
            @Parameter(name = "Pairing", description = "Filtro por maridaje solo para vinos", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "pairing") String pairing,
            @Parameter(name = "Brand", description = "Filtro por marca", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "brand") String brand,
            @Parameter(name = "Cepa", description = "Filtro por cepa", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "cepa") String cepa,
            @Parameter(name = "Club dislicores", description = "Club dislicores", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "levelClub") LevelClubEnum levelClub,
            @Parameter(name = "Tipo de documento C o N", description = "Tipo de documento", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "documentType") DocumentTypeEnum documentType,
            @Parameter(name = "Numero de documento", description = "Numero de documento", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "documentNumber") String documentNumber,
            @Parameter(name = "Premios", description = "Filtro por premios", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "companion") String companion,
            @Parameter(name = "alphabeth", description = "Realiza el ordenamiento mediante el nombre del producto por orden, valores validos: asc o desc, valor por defecto asc (A, B, C)", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "alphabeth") String alphabeth,
            @Parameter(name = "size", description = "Tamaño de la hoja de busqueda, valor por defecto 10", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "size") Integer size,
            @Parameter(name = "page", description = "Pagina de busqueda empezando desde 0, valor por defecto 0", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "page") Integer page,
            @Parameter(name = "orderBy", description = "Filtro por marca", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "orderBy") String orderBy,
            @Parameter(name = "orderFor", description = "Filtro por marca", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "orderFor") String orderFor,
            @Parameter(name = "search", description = "Filtro por palabra o frase", required = false, in = ParameterIn.QUERY) @RequestParam(required = false, value = "search") String search
    ) {
        ProductParamsDto productParamsDto = ProductParamsDto.builder().whitelabel(whitelabel).subcategory(subcategory).cepa(cepa).brand(brand).levelClub(levelClub)
                .documentType(documentType).documentNumber(documentNumber)
                .minRangePrice(minRangePrice).maxRangePrice(maxRangePrice).countryOriginCode(countryOriginCode).pairing(pairing).companion(companion).search(search).build();
        if (size != null) productParamsDto.setSize(size);
        if (page != null) productParamsDto.setPage(page);
        if (orderBy != null) productParamsDto.setOrderBy(orderBy);
        if (orderFor != null) productParamsDto.setOrderFor(orderFor);
        ProductsFilteredResponseDto productsFilteredResponseDto = productBusiness.getProducts(productParamsDto, token);
        return new ResponseDto<>(200, "OK", "Detalle de producto", productsFilteredResponseDto);
    }
}
