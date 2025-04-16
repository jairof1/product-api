package com.dislicores.api.b2c.app.product.business;

import com.dislicores.api.b2c.app.product.acl.dto.CategoryResponseElasticDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.AggresgationsDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.BrandBucket;
import com.dislicores.api.b2c.app.product.domain.aggregations.FilterBrandDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.FilterCepaDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.FilterCountryDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.FilterMaridajeDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.FilterRangePriceDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.FilterRewardDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.FiltersDto;
import com.dislicores.api.b2c.app.product.domain.aggregations.PriceBucket;
import com.dislicores.api.b2c.app.product.domain.banner.BannerDto;
import com.dislicores.api.b2c.app.product.domain.carrousel.CarrouselDetailResponse;
import com.dislicores.api.b2c.app.product.domain.carrousel.CarrouselHomeDto;
import com.dislicores.api.b2c.app.product.domain.carrousel.CarrouselResponse;
import com.dislicores.api.b2c.app.product.domain.carrousel.OriginSection;
import com.dislicores.api.b2c.app.product.domain.category.CategoryResponseDto;
import com.dislicores.api.b2c.app.product.domain.category.DocumentCategoryDto;
import com.dislicores.api.b2c.app.product.domain.category.SubCategoryDto;
import com.dislicores.api.b2c.app.product.domain.firebase.FirebaseCartDto;
import com.dislicores.api.b2c.app.product.domain.levelclub.LevelClubDto;
import com.dislicores.api.b2c.app.product.domain.middle.ListaPrecioInventarioDto;
import com.dislicores.api.b2c.app.product.domain.notification.NotificacionPushDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.DocumentProductB2BDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.DocumentProductDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductDetailDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductList;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductsBrandResponseDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.ProductsFilteredResponseDto;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoListSys;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoString;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.BaseEsCoSys;
import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.ProductCategory;
import com.dislicores.api.b2c.app.product.domain.promotions.ClientDto;
import com.dislicores.api.b2c.app.product.domain.promotions.PromotionRequestGenericDto;
import com.dislicores.api.b2c.app.product.domain.promotions.PromotionResponseGenericDto;
import com.dislicores.api.b2c.app.product.domain.promotions.PromotionSearchDto;
import com.dislicores.api.b2c.app.product.domain.promotions.RequestPromotionDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductInfoDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductParamListDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductParamsDto;
import com.dislicores.api.b2c.app.product.domain.request.ProductWishDto;
import com.dislicores.api.b2c.app.product.domain.request.vtex.ProductSearchDto;
import com.dislicores.api.b2c.app.product.domain.response.ResponseDto;
import com.dislicores.api.b2c.app.product.domain.response.ResponseSearchDto;
import com.dislicores.api.b2c.app.product.domain.response.SpecificationsDto;
import com.dislicores.api.b2c.app.product.domain.whitelabel.WhiteLabelActiveDto;
import com.dislicores.api.b2c.app.product.exception.ProductException;
import com.dislicores.api.b2c.app.product.model.search.IndexB2CNewSearch;
import com.dislicores.api.b2c.app.product.model.search.IndexB2CSearch;
import com.dislicores.api.b2c.app.product.model.vtex.ListItems;
import com.dislicores.api.b2c.app.product.model.vtex.ListItemsWrapper;
import com.dislicores.api.b2c.app.product.model.vtex.ListRoot;
import com.dislicores.api.b2c.app.product.provider.ElasticProvider;
import com.dislicores.api.b2c.app.product.provider.FCMProvider;
import com.dislicores.api.b2c.app.product.provider.NotificationPushProvider;
import com.dislicores.api.b2c.app.product.provider.PromotionProvider;
import com.dislicores.api.b2c.app.product.provider.VtexProvider;
import com.dislicores.api.b2c.app.product.repository.middle.PriceListRepository;
import com.dislicores.api.b2c.app.product.repository.search.IndexB2CNewSearchRepository;
import com.dislicores.api.b2c.app.product.repository.search.IndexB2CSearchRepository;
import com.dislicores.api.b2c.app.product.util.MessagesUtil;
import com.dislicores.api.b2c.app.product.util.ProductConstants;
import com.dislicores.api.b2c.app.product.util.TokenUtil;
import com.dislicores.api.b2c.app.product.util.UtilConverter;
import com.dislicores.api.b2c.app.product.util.ValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dislicores.api.b2c.app.product.domain.promotions.TypePromotionEnum.REGALO;
import static com.dislicores.api.b2c.app.product.util.ProductConstants.DPV006;
import static com.dislicores.api.b2c.app.product.util.ProductConstants.VTEXT_WISHLIST_LISTITEMS;
import static com.dislicores.api.b2c.app.product.util.UtilConverter.validNullValue;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductBusinessImpl implements ProductBusiness {

    private final IndexB2CNewSearchRepository indexB2BSearchRepository;
    private final IndexB2CSearchRepository indexB2CSearchRepository;
    private final ObjectMapper objectMapper;
    private final MessagesUtil messagesUtil;
    private final PriceListRepository priceListRepository;
    private final ValidationUtils validationUtils;
    private final ElasticProvider elasticProvider;
    private final VtexProvider vtexProvider;
    private final PromotionProvider promotionProvider;
    private static final String LIST_ITEMS = "ListItems";

    @Override
    public ProductsFilteredResponseDto getProducts(ProductParamsDto productParamsDto, String token) {
        log.info("Init getProducts {}", productParamsDto);
        this.validationUtils.validate(productParamsDto);
        ProductsFilteredResponseDto productsFilteredResponseDto = null;
        try {
            int sizePage = productParamsDto.getSize();
            int page = productParamsDto.getPage();

            String subCategoryParam = productParamsDto.getSubcategory();
            // Informacion del whitelabel
            WhiteLabelActiveDto whiteLabelActive = this.getWhitelabelByEntryId(productParamsDto.getWhitelabel());

            /** Retorna los codigos de referencia del whitelabel */
            // Informacion del whitelabel
            String wireLabel = whiteLabelActive.getFields().getWirelabelReference().findValue("es-CO").asText();                // Obtener codigo de bodega
            String listPrice = whiteLabelActive.getFields().getListPriceReference().findValue("es-CO").asText();                // Obtener codigo lista precio
            String operationCenter = whiteLabelActive.getFields().getOperationalCenterReference().findValue("es-CO").asText();  // Obtener codigo CO

            // seteada cuando se solicita una categoria vinos o se solicitan productos sin especificar la categoria
            boolean wineAggregations = true;
            if (subCategoryParam != null) wineAggregations = isWineCategory(subCategoryParam);

            ResponseEntity<JsonNode> responseTemplate = this.getQueryProducts(productParamsDto, listPrice, wireLabel, wineAggregations, null);

            JsonNode result = responseTemplate.getBody();
            ResponseSearchDto responseSearchDto = objectMapper.convertValue(result, ResponseSearchDto.class);

            List<IndexB2CSearch> productsB2C = new ArrayList<>();
            responseSearchDto.getHits().getHits().forEach(responseProductSearchDto ->
                    productsB2C.add(objectMapper.convertValue(responseProductSearchDto.get_source(), IndexB2CSearch.class))
            );

            List<ProductList> products = getListProductsCompleted(wireLabel, listPrice, productsB2C);
            List<ProductList> productsReturnedFromFavorites = setFavoriteProducts(token, products);
            List<ProductList> productsReturnedWithPromotionsApplied = setPromotionsToSkus(productsReturnedFromFavorites, operationCenter,
                    (productParamsDto.getDocumentType() != null) ? productParamsDto.getDocumentType().name() : null,
                    (productParamsDto.getDocumentNumber() != null) ? productParamsDto.getDocumentNumber() : null,
                    (productParamsDto.getLevelClub() != null) ? productParamsDto.getLevelClub().name() : null);

            Integer pages = 0;
            Integer totalElements = responseSearchDto.getHits().getTotal().getValue();
            if (sizePage > 0) {
                pages = (int) Math.ceil((double)totalElements / sizePage);
            }

            AggresgationsDto aggresgationsDto = objectMapper.convertValue(responseSearchDto.getAggregations(), AggresgationsDto.class);
            FiltersDto filtersDto = buildFilters(aggresgationsDto, wineAggregations);

            productsFilteredResponseDto = ProductsFilteredResponseDto.builder()
                    .productsFiltered(productsReturnedWithPromotionsApplied).totalElements(totalElements).totalPages(pages).currentPage(page).build();
            if (productParamsDto.getPage() == 0) productsFilteredResponseDto.setFilters(filtersDto);

        } catch (Exception ex) {
            throw ex;
        }
        return productsFilteredResponseDto;
    }

    private boolean isWineCategory(String subCategoryParam) {
        String wineCategory = indexB2BSearchRepository.findCategoryWine(PageRequest.of(0, 100)).getContent().get(0).getEntryId();
        String arrayCategories = getCategories(wineCategory).getQueryCategory();
        if (!arrayCategories.isEmpty()) {
            try {
                JsonNode objectJsonNode = objectMapper.readTree(arrayCategories).findValue("terms").findValue("fields.subcategoria.es-CO.sys.id.keyword");
                List<String> listSubCategories = objectMapper.convertValue(objectJsonNode, new TypeReference<>() {});
                Optional<String> subCategoryWindFound = listSubCategories.stream().filter(cat -> cat.equals(subCategoryParam)).findFirst();
                if (subCategoryWindFound.isPresent()) {
                    return true;
                }
            } catch (JsonProcessingException e) {
                exceptionBadRequest();
            }
        }
        return false;
    }

    private String getOrderQuery (ProductParamsDto productParamsDto, String listPrice, String wireLabel) {
        /** Variables de paginacion */
        int page = productParamsDto.getPage();
        int sizePage = productParamsDto.getSize();
        int pageFrom = page * sizePage;

        String orderByParam = productParamsDto.getOrderBy();
        String orderForParam = productParamsDto.getOrderFor();

        List<String> listSkuPrioritized = validPrioritization(productParamsDto.getSubcategory(), listPrice, wireLabel);

        // Ordenamientos
        if (Objects.nonNull(orderByParam)) {
            if (orderByParam.equals("price"))
                return buildSortQueryPrice(orderForParam, listPrice, wireLabel, sizePage, pageFrom, listSkuPrioritized);
            else if (orderByParam.equals("stock"))
                return buildSortQueryInventory(orderForParam, listPrice, wireLabel, sizePage, pageFrom, listSkuPrioritized);
            else if (orderByParam.equals("alphabeth"))
                return buildSortQueryAlphabeth(orderForParam, sizePage, pageFrom, listPrice, wireLabel, listSkuPrioritized);
            else if (orderByParam.equals("score"))
                return buildSortQueryScore(listPrice, wireLabel, sizePage, pageFrom, listSkuPrioritized);
        }

        return """
            "size": "%s",
            "from": "%s",
            %s
            """.formatted(sizePage, pageFrom, getAggregations());
    }

    private List<String> validPrioritization(String subcategory, String listPrice, String wireLabel){

        if(Objects.isNull(subcategory) || Objects.isNull(listPrice) || Objects.isNull(wireLabel)){
            return Collections.emptyList();
        }
        Page<IndexB2CNewSearch> indexB2BSearches = indexB2BSearchRepository.findSkuPrioritized(List.of(subcategory), PageRequest.of(0, 100));
        if (indexB2BSearches == null) {
            return Collections.emptyList();
        }
        List<CategoryResponseElasticDto> list = new ArrayList<>();
        indexB2BSearches.forEach(indexB2BSearch1 -> list.add(objectMapper.convertValue(indexB2BSearch1.getFields(), CategoryResponseElasticDto.class)));

        if(list.isEmpty() || Objects.isNull(list.getFirst().getProducto())){
            return Collections.emptyList();
        }
        list.forEach(limit -> {
            if(Objects.isNull(limit.getProducto()) || Objects.isNull(limit.getProducto().getEsCO())){
                return;
            }
            List<String> skus = limit.getProducto().getEsCO().stream()
                    .map(jsonNode -> {
                        var id = jsonNode.path("sys").path("id").asText();
                        return indexB2BSearchRepository.findProductsById(id)
                                .map(index -> index.getFields().path("sku").path("es-CO").asText())
                                .orElse(null);
                    })
                    .filter(Objects::nonNull)
                    .toList();
            limit.setSku(skus);
        });

        var actualDate = LocalDate.now();
        if (Objects.nonNull(list.getFirst().getInitialDate()) && actualDate.isBefore(list.getFirst().getInitialDate().getEsCO()))
            return Collections.emptyList();
        if (Objects.nonNull(list.getFirst().getFinalDate()) && actualDate.isAfter(list.getFirst().getFinalDate().getEsCO()))
            return Collections.emptyList();

        List<String> listSku = new ArrayList<>();
        list.getFirst().getSku().forEach(sku -> {
            int inventary;
            List<ListaPrecioInventarioDto> listaPrecioInventarioDtos = priceListRepository.findListaPrecioBySkusAndWhitelabelInfo(new HashSet<>(List.of(sku)), wireLabel, listPrice);
            if (!listaPrecioInventarioDtos.isEmpty() && Objects.nonNull(listaPrecioInventarioDtos.getFirst()) && Objects.nonNull(listaPrecioInventarioDtos.getFirst().getInventary())) {
                inventary = listaPrecioInventarioDtos.getFirst().getInventary();
                log.debug("Hay en el inventario {} unidades del sku : {}", inventary, sku);
                if(inventary > 0){
                    listSku.add(sku);
                }
            }
        });
        return listSku;
    }

    private ResponseEntity<JsonNode> getQueryProducts(ProductParamsDto productParamsDto, String listPrice, String wireLabel, boolean wineAggregations, List<String> listSkus) {
        String subCategoryParam = productParamsDto.getSubcategory();
        Integer minRangePriceParam = productParamsDto.getMinRangePrice();
        Integer maxRangePriceParam = productParamsDto.getMaxRangePrice();
        String countryOriginCodeParam = productParamsDto.getCountryOriginCode();
        String pairingParam = productParamsDto.getPairing();
        String brandParam = productParamsDto.getBrand();
        String cepaParam = productParamsDto.getCepa();
        String companionParam = productParamsDto.getCompanion();


        /** Cadenas de texto usadas para filtros acumulativos */
        List<String> filtersList = new ArrayList<>();

        String querySort = getOrderQuery(productParamsDto, listPrice, wireLabel);
        String rangePrice = "";

        /** Si se realiza busqueda por palabra o frase */
        if (productParamsDto.getSearch() != null) {
            var searchQuery = this.getSearchWord(productParamsDto.getSearch());
            if (!searchQuery.isEmpty()) filtersList.add(searchQuery);
        }

        /** si @subCategoryParam existe, obtener las categorias y subcategorias hijas */
        if (subCategoryParam != null) {
            var catSubcategory = getCategories(subCategoryParam).getQueryCategory();
            if (!catSubcategory.isEmpty()) filtersList.add(catSubcategory);
        }

        if (listSkus != null && !listSkus.isEmpty()) {
            String subQuerySkus = listSkus.stream().map(skuId -> "\"" + skuId + "\"").collect(Collectors.joining(","));
            var querySkus = """
                    {
                        "terms": {
                            "fields.sku.es-CO.keyword": [%s]
                        }
                    }
                    """.formatted(subQuerySkus);
            filtersList.add(querySkus);
        }

        /** si @minRangePriceParam y @maxRangePriceParam existe, obtener consulta para rangos de precio */
        if (minRangePriceParam != null && maxRangePriceParam != null && minRangePriceParam >= 0 && maxRangePriceParam >= 0) {
            if (maxRangePriceParam > minRangePriceParam) {
                rangePrice = getRangePriceQuery(rangePrice, minRangePriceParam, maxRangePriceParam);
            } else {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF006,
                        this.messagesUtil.getMessage(ProductConstants.DPF006));
            }
        } else if (minRangePriceParam != null ^ maxRangePriceParam != null) {
            throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF007,
                    this.messagesUtil.getMessage(ProductConstants.DPF007));
        }

        /**  */
        if (brandParam != null) {
            if (brandParam.trim().isEmpty()) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF001,
                        this.messagesUtil.getMessage(ProductConstants.DPF001));
            }
            var brand = getBrandQuery("", brandParam);
            if (!brand.isEmpty()) filtersList.add(brand);
        }

        /**  */
        if (countryOriginCodeParam != null) {
            if (countryOriginCodeParam.trim().isEmpty()) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF002,
                        this.messagesUtil.getMessage(ProductConstants.DPF002));
            }
            if (!wineAggregations) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF008,
                        this.messagesUtil.getMessage(ProductConstants.DPF008));
            }
            var countryOrigin = getCountryOriginQuery("", countryOriginCodeParam);
            if (!countryOrigin.isEmpty()) filtersList.add(countryOrigin);
        }

        /**  */
        if (cepaParam != null) {
            if (cepaParam.trim().isEmpty()) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF001,
                        this.messagesUtil.getMessage(ProductConstants.DPF001));
            }
            if (!wineAggregations) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPA025,
                        this.messagesUtil.getFormatedMessage(ProductConstants.DPA025, new Object[]{"cepas", "vinos"}));

            }
            var cepa = getCepaQuery("", cepaParam);
            if (!cepa.isEmpty()) filtersList.add(cepa);
        }

        /**  */
        if (pairingParam != null) {
            if (pairingParam.trim().isEmpty()) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF002,
                        this.messagesUtil.getMessage(ProductConstants.DPF002));
            }
            if (!wineAggregations) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF008,
                        this.messagesUtil.getMessage(ProductConstants.DPF008));
            }
            var pairing = getPairingQuery("", pairingParam);
            if (!pairing.isEmpty()) filtersList.add(pairing);
        }

        /**  */
        if (companionParam != null) {
            if (companionParam.trim().isEmpty()) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF001,
                        this.messagesUtil.getMessage(ProductConstants.DPF001));
            }
            if (!wineAggregations) {
                throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPA025,
                        this.messagesUtil.getFormatedMessage(ProductConstants.DPA025, new Object[]{"premios", "vinos"}));
            }
            var companion = getCompanionQuery("", companionParam);
            if (!companion.isEmpty()) filtersList.add(companion);
        }

        String filters = String.join(",", filtersList);

        /** Construir querys */
        String buildQuery = getFullQueryBuilded(filters, listPrice, wireLabel, rangePrice, querySort);

        JsonNode query = null;
        try {
            query = objectMapper.readTree(buildQuery);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        /** provider */
        ResponseEntity<JsonNode> responseTemplate = elasticProvider.sendFullQuery(query);
        /** provider */
        return responseTemplate;
    }

    private FiltersDto buildFilters(AggresgationsDto aggresgationsDto, boolean wineAggregations) {
        /** Construye los aggregations */
        PriceBucket maxPriceBucket = aggresgationsDto.getMaxPrice();
        PriceBucket minPriceBucket = aggresgationsDto.getMinPrice();
        List<BrandBucket> brandAggresgationDto = new ArrayList<>();
        if (aggresgationsDto.getBrandsDetected() != null)
            brandAggresgationDto = aggresgationsDto.getBrandsDetected().getBuckets();
        if (aggresgationsDto.getAlcoholLevelDetected() != null)
            aggresgationsDto.getAlcoholLevelDetected().getBuckets();
        List<BrandBucket> countryAggresgationDto = new ArrayList<>();
        if (aggresgationsDto.getCountryDetected() != null)
            countryAggresgationDto = aggresgationsDto.getCountryDetected().getBuckets();
        List<BrandBucket> cepaAggresgationDto = new ArrayList<>();
        if (aggresgationsDto.getCepaDetected() != null)
            cepaAggresgationDto = aggresgationsDto.getCepaDetected().getBuckets();
        List<BrandBucket> maridajeAggresgationDto = new ArrayList<>();
        if (aggresgationsDto.getMaridajeDetected() != null)
            maridajeAggresgationDto = aggresgationsDto.getMaridajeDetected().getBuckets();
        List<BrandBucket> rewardAggresgationDto = new ArrayList<>();
        if (aggresgationsDto.getRewardDetected() != null)
            rewardAggresgationDto = aggresgationsDto.getRewardDetected().getBuckets();

        Map<String, Integer> minRangePriceMap = new HashMap<>();
        minRangePriceMap.put("minRangePrice", minPriceBucket.getMinPriceWhitelabel().get("value"));
        Map<String, Integer> maxRangePriceMap = new HashMap<>();
        maxRangePriceMap.put("maxRangePrice", maxPriceBucket.getMaxPriceWhitelabel().get("value"));

        FiltersDto filtersDto = null;
        if (wineAggregations) {
            /** Construccion de los aggregations */
            filtersDto = FiltersDto.builder()
                    .brands(FilterBrandDto.builder().title("Marcas").items(brandAggresgationDto).build())
                    .rangePrice(FilterRangePriceDto.builder().title("Rango de precios").items(Arrays.asList(minRangePriceMap, maxRangePriceMap)).build())
                    .country(FilterCountryDto.builder().title("País de origen").items(countryAggresgationDto).build())
                    .cepa(FilterCepaDto.builder().title("Cepa de vinos").items(cepaAggresgationDto).build())
                    .maridaje(FilterMaridajeDto.builder().title("Acompañante de vinos").items(maridajeAggresgationDto).build())
                    .reward(FilterRewardDto.builder().title("Premios").items(rewardAggresgationDto).build())
                    .build();
        } else {
            /** Construccion de los aggregations */
            filtersDto = FiltersDto.builder()
                    .brands(FilterBrandDto.builder().title("Marcas").items(brandAggresgationDto).build())
                    .rangePrice(FilterRangePriceDto.builder().title("Rango de precios").items(Arrays.asList(minRangePriceMap, maxRangePriceMap)).build())
                    .country(FilterCountryDto.builder().title("País de origen").items(Arrays.asList()).build())
                    .cepa(FilterCepaDto.builder().title("Cepa de vinos").items(Arrays.asList()).build())
                    .maridaje(FilterMaridajeDto.builder().title("Acompañante de vinos").items(Arrays.asList()).build())
                    .reward(FilterRewardDto.builder().title("Premios").items(Arrays.asList()).build())
                    .build();
        }
        return filtersDto;
    }

    private List<ProductList> setPromotionsToSkus(List<ProductList> productsToApplyPromotions, String operationCenter,
                                                  String documentType, String documentNumber, String levelClub) {

        PromotionRequestGenericDto promotionRequestGenericDto = new PromotionRequestGenericDto();
        promotionRequestGenericDto.setOperationCenter(operationCenter);
        promotionRequestGenericDto.setClient(new PromotionRequestGenericDto.ClientPromotionRequestDto
                (documentType, documentNumber, (levelClub != null && !(levelClub.isBlank()) && !(levelClub.isEmpty())) ? levelClub : null));


        List<PromotionRequestGenericDto.PromotionProductListRequestDto> productsToRequest = new ArrayList<>();

        productsToApplyPromotions.stream().parallel().forEachOrdered(productList -> {
            productList.setIco((productList.getIco() != null) ? productList.getIco() : 0);
            productList.setIva((productList.getIva() != null) ? productList.getIva() : 0);
            PromotionRequestGenericDto.PromotionProductListRequestDto productToRequest = new PromotionRequestGenericDto.PromotionProductListRequestDto();
            productToRequest.setSku(productList.getIdSku());
            productToRequest.setIco(BigDecimal.valueOf(productList.getIco()));
            productToRequest.setIva(BigDecimal.valueOf(productList.getIva()));
            productToRequest.setUnitPrice(BigDecimal.valueOf((productList.getPrice() != null) ? productList.getPrice() : 0));
            productsToRequest.add(productToRequest);
        });

        promotionRequestGenericDto.setProductDtoList(productsToRequest);

        PromotionResponseGenericDto returnMapped = objectMapper.convertValue(
                Objects.requireNonNull(promotionProvider.getPromotionsByListSku(promotionRequestGenericDto).getBody()).findValue("data"), PromotionResponseGenericDto.class);


        productsToApplyPromotions.stream().parallel().forEachOrdered(productList -> {
            Optional<PromotionResponseGenericDto.PromotionProductListResponseDto> productFoundMapped = returnMapped.getProductDtoList().parallelStream()
                    .filter(productReturnedMapped -> productReturnedMapped.getSku().equals(productList.getIdSku()))
                    .findFirst();

            productFoundMapped.ifPresentOrElse(
                    productFound -> {
                        productList.setBestPriceClubMembers(productFound.getBestPriceClubMembers());
                        productList.setBestPriceClubMembersLevel(productFound.getBestPriceClubMembersLevel());
                        if (Objects.nonNull(productFound.getPromotion()))
                            calculateProductPromotion(productList, productFound);
                    },
                    () -> calculateWithoutPromotion(productList)
            );
        });
        return productsToApplyPromotions;
    }

    private ProductList calculateProductPromotion(ProductList product, PromotionResponseGenericDto.PromotionProductListResponseDto productFoundMapped) {
        log.debug("TYPE {}", productFoundMapped.getPromotion().getType());
        log.debug("SKU {}", productFoundMapped.getSku());
        switch (productFoundMapped.getPromotion().getType()) {
            case OFERTA:
                product = setInfoPrice(product, productFoundMapped);
                break;
            case REGALO:
                ProductList updatedProductGift = setInfoPrice(product, productFoundMapped);
                if (productFoundMapped.getPromotion().getType() == REGALO && StringUtils.isNotBlank(productFoundMapped.getPromotion().getSkuGiftName())) {
                    updatedProductGift.setName(updatedProductGift.getName() + " + " + productFoundMapped.getPromotion().getSkuGiftName());
                }
                product = updatedProductGift;
                break;
            default:
                calculePercentage(product, productFoundMapped);
                break;
        }
        product.setTags(productFoundMapped.getPromotion().getTag());
        product.setProductWithStaggeredPromotion(productFoundMapped.isProductWithStaggeredPromotion());
        return product;
    }

    private ProductList setInfoPrice(ProductList product, PromotionResponseGenericDto.PromotionProductListResponseDto productFoundMapped) {
        ProductList updatedProduct = calculateWithoutPromotion(product);
        updatedProduct.getInfoPrice().setPromotionId(productFoundMapped.getPromotion().getPromotionId());
        updatedProduct.getInfoPrice().setPromotionType(productFoundMapped.getPromotion().getType().name());
        return updatedProduct;
    }

    private ProductList calculateWithoutPromotion(ProductList productToApplyPromotion) {
        BigDecimal totalPrice = BigDecimal.valueOf(productToApplyPromotion.getPrice());
        BigDecimal ico = (!(productToApplyPromotion.getIco().isNaN())) ?
                BigDecimal.valueOf(productToApplyPromotion.getIco()) : BigDecimal.ZERO;
        BigDecimal subTotalPrice = totalPrice.subtract(ico);
        BigDecimal iva = (!(productToApplyPromotion.getIva().isNaN())) ? subTotalPrice
                .multiply(BigDecimal.valueOf(productToApplyPromotion.getIva())).divide(BigDecimal.valueOf(100)) : BigDecimal.ZERO;
        subTotalPrice = subTotalPrice.subtract(iva);
        productToApplyPromotion.setInfoPrice(
                ProductList.InfoPriceDto.builder().subTotalPrice(subTotalPrice).iva(iva).ico(ico).totalDiscount(BigDecimal.ZERO)
                        .totalPrice(BigDecimal.valueOf(productToApplyPromotion.getPrice())).build()
        );
        return productToApplyPromotion;
    }

    private ProductList calculePercentage(ProductList productToApplyPromotion, PromotionResponseGenericDto.PromotionProductListResponseDto productMapped) {
        productToApplyPromotion.setInfoPrice(
                ProductList.InfoPriceDto.builder()
                        .subTotalPrice(productMapped.getDiscount().getSubtotalPrice())
                        .iva(productMapped.getDiscount().getIva())
                        .ico(productMapped.getDiscount().getIco())
                        .totalDiscount(productMapped.getDiscount().getTotalDiscount())
                        .totalPrice(productMapped.getDiscount().getTotalPrice())
                        .promotionId(productMapped.getPromotion().getPromotionId())
                        .promotionType(productMapped.getPromotion().getType().name())
                        .build()
        );
        productToApplyPromotion.setDiscountPercent((double) productMapped.getPromotion().getDiscount());
        return productToApplyPromotion;
    }

    private String getSearchWord(String searchWord) {
        List<String> wordsToFindFuzzy = new ArrayList<>();

        Arrays.asList(searchWord.split(" ")).forEach(word ->
                wordsToFindFuzzy.add("{\"fuzzy\":{\"fields.nombre.es-CO\":{\"value\":\"" + word + "\",\"fuzziness\":\"5\"}}},")
        );
        StringBuilder queryFuzzy = new StringBuilder();
        for (String wordSearch : wordsToFindFuzzy) {
            queryFuzzy.append(wordSearch);
        }

        var querySearch = """
            {
                "bool": {
                    "should": [
                        {
                            "match": {
                                "fields.nombre.es-CO": "%s"
                            }
                        },
                        %s
                        {
                            "match": {
                                "fields.sku.es-CO.keyword": "%s"
                            }
                        }
                    ]
                }
            }
            """.formatted(searchWord, queryFuzzy, searchWord);
        return querySearch;
    }

    private CategoryResponseDto getCategories(String entryId) {
        Page<IndexB2CSearch> searchCategories = indexB2CSearchRepository.findCategories(PageRequest.of(0, 50));
        String queryFilterCategory = "";
        if (searchCategories.getSize() > 0) {
            DocumentCategoryDto categoryList = objectMapper.convertValue(searchCategories.getContent().get(0).getFields(), DocumentCategoryDto.class);
            List<String> searchCategoryNode = new ArrayList<>();
            searchNode(categoryList.getCategoriesDocument(), searchCategoryNode, entryId, false);
            if (searchCategoryNode.size() > 0) {
                StringBuilder sb1 = new StringBuilder();
                if (searchCategoryNode.size() > 0) {
                    sb1.append("\"");
                    sb1.append(searchCategoryNode.get(0));
                    sb1.append("\"");
                }
                if (searchCategoryNode.size() > 1) {
                    for (int i = 1; i < searchCategoryNode.size(); i++) {
                        sb1.append(",");
                        sb1.append("\"");
                        sb1.append(searchCategoryNode.get(i));
                        sb1.append("\"");
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("""
                        {
                            "terms": {
                              "fields.subcategoria.es-CO.sys.id.keyword": [
                        """);
                sb2.append(sb1);
                sb2.append("""
                              ]
                            }
                          }
                        """);
                queryFilterCategory = sb2.toString();
            }
        }
        CategoryResponseDto categoryResponseDto = CategoryResponseDto
                .builder().queryCategory(queryFilterCategory).build();
        return categoryResponseDto;
    }

    private void searchNode(List<SubCategoryDto> categoryList, List<String> nodeList, String value, Boolean isActive) {
        if (categoryList == null) return;

        for (int i = 0; i < categoryList.size(); i++) {
            SubCategoryDto category = categoryList.get(i);
            List<SubCategoryDto> subcategoryList = category.getFields().getSubCategoryDtoList();
            if (category.getEntryId().equals(value) || isActive) {
                nodeList.add(category.getEntryId());
                searchNode(subcategoryList, nodeList, value, true);
            } else {
                searchNode(subcategoryList, nodeList, value, false);
            }
        }
    }

    private String buildScriptSort(List<String> skuOrder) {
        if (skuOrder == null || skuOrder.isEmpty()) {
            return "";
        }

        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("if (doc['fields.sku.es-CO.keyword'].value == '").append(skuOrder.getFirst()).append("') { return '0'; }");

        for (int i = 1; i < skuOrder.size(); i++) {
            scriptBuilder.append(" else if (doc['fields.sku.es-CO.keyword'].value == '").append(skuOrder.get(i)).append("') { return '").append(i).append("'; }");
        }

        scriptBuilder.append(" else { return '").append(skuOrder.size()).append("'; }");

        return "    {\n" +
                "      \"_script\": {\n" +
                "        \"type\": \"string\",\n" +
                "        \"script\": {\n" +
                "          \"source\": \"" + scriptBuilder + "\"\n" +
                "        },\n" +
                "        \"order\": \"asc\"\n" +
                "      }\n" +
                "    },";
    }

    private String buildSortQueryPrice(String orderForParam, String listPrice, String wireLabel, Integer sizePage, int pageFrom, List<String> listSkuPrioritized) {

        return "  \"sort\": [\n" +
                buildScriptSort(listSkuPrioritized)+
                "    {\n" +
                "      \"fields.listaPrecioInventarioObjectDtos5.withStock\": {\n" +
                "        \"order\": \"desc\",\n" +
                "        \"missing\": \"_last\", \n" +
                "        \"nested_path\": \"fields.listaPrecioInventarioObjectDtos5\",\n" +
                "        \"nested_filter\": {\n" +
                "          \"bool\": {\n" +
                "            \"must\": [\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.listPrice\": \"" + listPrice + "\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.bodega\": \"" + wireLabel + "\"\n" +
                "                }\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }, " +
                "    {\n" +
                "      \"fields.listaPrecioInventarioObjectDtos5.price\": {\n" +
                "        \"order\": \"" + orderForParam + "\",\n" +
                "        \"nested_path\": \"fields.listaPrecioInventarioObjectDtos5\",\n" +
                "        \"nested_filter\": {\n" +
                "\"bool\": {\n" +
                "                \"must\": [\n" +
                "                  {\n" +
                "                    \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.listPrice\": \"" + listPrice + "\"\n" +
                "                    }\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.bodega\": \"" + wireLabel + "\"\n" +
                "                    }\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"size\": " + sizePage + ",\n" +
                "  \"from\": " + pageFrom + ",\n" +
                getAggregations() +
                "}";
    }

    private String buildSortQueryInventory(String orderForParam, String listPrice, String wireLabel, Integer sizePage, int pageFrom, List<String> listSkuPrioritized) {
        return "  \"sort\": [\n" +
                buildScriptSort(listSkuPrioritized)+
                "    {\n" +
                "      \"fields.listaPrecioInventarioObjectDtos5.withStock\": {\n" +
                "        \"order\": \"desc\",\n" +
                "        \"missing\": \"_last\", \n" +
                "        \"nested_path\": \"fields.listaPrecioInventarioObjectDtos5\",\n" +
                "        \"nested_filter\": {\n" +
                "          \"bool\": {\n" +
                "            \"must\": [\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.listPrice\": \"" + listPrice + "\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.bodega\": \"" + wireLabel + "\"\n" +
                "                }\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }, " +
                "    {\n" +
                "      \"fields.listaPrecioInventarioObjectDtos5.inventary\": {\n" +
                "        \"order\": \"" + orderForParam + "\",\n" +
                "        \"nested_path\": \"fields.listaPrecioInventarioObjectDtos5\",\n" +
                "        \"nested_filter\": {\n" +
                "\"bool\": {\n" +
                "                \"must\": [\n" +
                "                  {\n" +
                "                    \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.listPrice\": \"" + listPrice + "\"\n" +
                "                    }\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.bodega\": \"" + wireLabel + "\"\n" +
                "                    }\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"size\": " + sizePage + ",\n" +
                "  \"from\": " + pageFrom + ",\n" +
                getAggregations() +
                "}";
    }

    private String buildSortQueryAlphabeth(String orderForParam, Integer sizePage, int pageFrom, String listPrice, String wireLabel, List<String> listSkuPrioritized) {
        return "  \"sort\": [\n" +
                buildScriptSort(listSkuPrioritized)+
                "    {\n" +
                "      \"fields.listaPrecioInventarioObjectDtos5.withStock\": {\n" +
                "        \"order\": \"desc\",\n" +
                "        \"missing\": \"_last\", \n" +
                "        \"nested_path\": \"fields.listaPrecioInventarioObjectDtos5\",\n" +
                "        \"nested_filter\": {\n" +
                "          \"bool\": {\n" +
                "            \"must\": [\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.listPrice\": \"" + listPrice + "\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.bodega\": \"" + wireLabel + "\"\n" +
                "                }\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }, " +
                "    {\n" +
                "      \"fields.nombre.es-CO.keyword\": {\n" +
                "        \"order\": \"" + orderForParam + "\"\n" +
                "      }\n" +
                "    }\n" +
                "  ], \n" +
                "  \"size\": " + sizePage + ",\n" +
                "  \"from\": " + pageFrom + ",\n" +
                getAggregations() +
                "}";
    }

    private String buildSortQueryScore(String listPrice, String wireLabel, Integer sizePage, int pageFrom, List<String> listSkuPrioritized) {
        return "\"sort\": [\n" +
                buildScriptSort(listSkuPrioritized)+
                "    {\n" +
                "      \"fields.listaPrecioInventarioObjectDtos5.withStock\": {\n" +
                "        \"order\": \"desc\",\n" +
                "        \"missing\": \"_last\",\n" +
                "        \"nested_path\": \"fields.listaPrecioInventarioObjectDtos5\",\n" +
                "        \"nested_filter\": {\n" +
                "          \"bool\": {\n" +
                "            \"must\": [\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                  \"fields.listaPrecioInventarioObjectDtos5.listPrice\": \"" + listPrice + "\"\n" +
                "                }\n" +
                "              },\n" +
                "              {\n" +
                "                \"match\": {\n" +
                "                  \"fields.listaPrecioInventarioObjectDtos5.bodega\": \"" + wireLabel + "\"\n" +
                "                }\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    { \"_score\": { \"order\": \"desc\" } }\n" +
                "  ],\n" +
                "  \"size\": " + sizePage + ",\n" +
                "  \"from\": " + pageFrom + ",\n" + getAggregations() + "}";
    }

    private String getRangePriceQuery(String rangePrice, Integer minRangePriceParam, Integer maxRangePriceParam) {
        return rangePrice.concat(",\n" +
                "                  {\n" +
                "                    \"range\": {\n" +
                "                      \"fields.listaPrecioInventarioObjectDtos5.price\": {\n" +
                "                        \"gte\": " + minRangePriceParam + ",\n" +
                "                        \"lte\": " + maxRangePriceParam + "\n" +
                "                      }\n" +
                "                      }\n" +
                "                    }");
    }

    private String getBrandQuery(String brand, String brandParam) {
        return brand.concat("{\n" +
                "        	\"term\": {\n" +
                "          	  \"fields.brandDto.fields.nombreMarca.es-CO.keyword\": \"" + brandParam + "\"\n" +
                "            }\n" +
                "         }");
    }

    private String getCountryOriginQuery(String countryOrigin, String countryOriginCodeParam) {
        return countryOrigin.concat("{\n" +
                "        \"term\": {\n" +
                "          \"fields.countryDto.fields.nombrePais.es-CO.keyword\": \"" + countryOriginCodeParam + "\"\n" +
                "        }}");
    }

    private String getCepaQuery(String cepa, String cepaParam) {
        return cepa.concat("{\n" +
                "        	\"term\": {\n" +
                "          	  \"fields.b2cCepa.es-CO.keyword\": \"" + cepaParam + "\"\n" +
                "            }\n" +
                "         }");
    }

    private String getPairingQuery(String pairing, String pairingParam) {
        return pairing.concat("{\n" +
                "        	\"match\": {\n" +
                "          	  \"fields.specifications.pairing\": \"" + pairingParam + "\"\n" +
                "            }\n" +
                "         }");
    }

    private String getCompanionQuery(String companion, String companionParam) {
        return companion.concat("{\n" +
                "        	\"term\": {\n" +
                "          	  \"fields.premiosB2c.es-CO.keyword\": \"" + companionParam + "\"\n" +
                "            }\n" +
                "         }");
    }

    private String getFullQueryBuilded(String filters, String listPrice, String wireLabel, String rangePrice, String querySort) {
        var filtersByListPriceAndWirelabel = """
            {
                "term":{
                    "contentType.keyword": {
                        "value": "producto"
                    }
                }
            },
            {
                "nested": {
                    "path": "fields.listaPrecioInventarioObjectDtos5",
                    "query": {
                        "bool": {
                            "must": [
                                {
                                    "match": {
                                        "fields.listaPrecioInventarioObjectDtos5.listPrice": "%s"
                                    }
                                },
                                {
                                    "match": {
                                        "fields.listaPrecioInventarioObjectDtos5.bodega": "%s"
                                    }
                                }
                                %s
                            ]
                        }
                    }
                }
            }
            """.formatted(listPrice, wireLabel, rangePrice);

        if (filters.isEmpty()) filters = filtersByListPriceAndWirelabel;
        else filters = filters.concat(",").concat(filtersByListPriceAndWirelabel);

        var query = """
            {
                "query": {
                    "bool": {
                        "must": [
                            %s
                        ]
                    }
                },
                %s
            }
            
            """.formatted(filters, querySort);
        return query;
    }

    private String getAggregations() {
        return """
                  "aggs": {
                    "brandsDetected": {
                      "terms": {
                        "field": "fields.brandDto.fields.nombreMarca.es-CO.keyword",
                        "size": 1000
                      }
                    },
                    "alcoholLevelDetected": {
                      "terms": {
                        "field": "fields.nivelDeAlcohol.es-CO",
                        "size": 1000
                      }
                    },
                    "maxPrice": {
                      "nested": {
                        "path": "fields.listaPrecioInventarioObjectDtos5"
                      },
                      "aggs": {
                        "maxPriceWhitelabel": {
                          "max": {
                            "field": "fields.listaPrecioInventarioObjectDtos5.price"
                          }
                        }
                      }
                    },
                    "minPrice": {
                      "nested": {
                        "path": "fields.listaPrecioInventarioObjectDtos5"
                      },
                      "aggs": {
                        "minPriceWhitelabel": {
                          "min": {
                            "field": "fields.listaPrecioInventarioObjectDtos5.price"
                          }
                        }
                      }
                    },
                    "countryDetected": {
                      "terms": {
                        "field": "fields.countryDto.fields.nombrePais.es-CO.keyword",
                        "size": 1000
                      }
                    },
                    "cepaDetected": {
                      "terms": {
                        "field": "fields.b2cCepa.es-CO.keyword",
                        "size": 1000
                      }
                    },
                    "maridajeDetected": {
                      "terms": {
                        "field": "fields.acompanante.es-CO.keyword",
                        "size": 1000
                      }
                    },
                    "rewardDetected": {
                      "terms": {
                        "field": "fields.premiosB2c.es-CO.keyword",
                        "size": 1000
                      }
                    }
                  }
                """;
    }
    /**
     * @Param: entryId de contentful para obtener informacion del whitelabel
     * @return: Retorna la informacion del whitelabel formateado con WhiteLabelActiveDto
     */
    public WhiteLabelActiveDto getWhitelabelByEntryId(String entryIdWhitelabel) {
        /** retorna el whitelabel. */
        Page<IndexB2CNewSearch> searchWhitelabels = indexB2BSearchRepository.findWhitelabelByEntryId(entryIdWhitelabel, PageRequest.of(0, 100));

        /** Si el whitelabel no existe o regresa mas de uno lazar excepcion. */
        if (searchWhitelabels.getTotalElements() != 1) {
            log.error("getRecomendedProducts: whitelabel {} no existe", entryIdWhitelabel);
            throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF004, this.messagesUtil.getMessage(ProductConstants.DPF004));
        }

        // Mapeo y validacion del whitelabel
        WhiteLabelActiveDto whiteLabelActive = objectMapper.convertValue(searchWhitelabels.getContent().get(0), WhiteLabelActiveDto.class);
        if (whiteLabelActive.getFields().getActiveWhitelabel() == null || whiteLabelActive.getFields().getActiveWhitelabel().findValue("es-CO").asText().equals("Deshabilitada")) {
            exceptionBadRequest();
        }
        return whiteLabelActive;
    }

    private void exceptionBadRequest() {
        throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPF005,
                this.messagesUtil.getMessage(ProductConstants.DPF005));
    }


    public List<ProductList> getListProductsCompleted(String wireLabel, String listPrice, List<IndexB2CSearch> productsFoundB2C) {

        List<ProductList> productsToReturn = new ArrayList<>();
        List<ListaPrecioInventarioDto> productsFromMiddleLayer = priceListRepository.findListaPrecioBySkusAndWhitelabelInfo(addSkuProductsMiddleLayer(productsFoundB2C), wireLabel, listPrice);
        productsFoundB2C.stream().parallel().forEachOrdered(productB2CDto -> {
            DocumentProductDto productResponse = new ObjectMapper().convertValue(productB2CDto, DocumentProductDto.class);
            if (productsFromMiddleLayer.parallelStream().filter(productFromMiddleLayer -> productFromMiddleLayer.getSkuId().equals(productResponse.getFields().getSku().getEsCO())).findFirst().isEmpty()) {
                log.error("Producto con sku {} no encontrado.", productResponse.getFields().getSku());
                return;
            }

            Double iva = validNullValue(productsFromMiddleLayer.stream().parallel().filter(productFromMiddleLayer -> productFromMiddleLayer.getSkuId().equals(productResponse.getFields().getSku().getEsCO())).findFirst().get().getIva());
            Double ico = validNullValue(productsFromMiddleLayer.stream().parallel().filter(productFromMiddleLayer -> productFromMiddleLayer.getSkuId().equals(productResponse.getFields().getSku().getEsCO())).findFirst().get().getIco());
            ProductList productList = ProductList.builder()
                    .name((productResponse.getFields().getNombre() == null) ? null : productResponse.getFields().getNombre().getEsCO())
                    .idProduct((productResponse.getFields().getB2cIdProducto() == null) ? null : productResponse.getFields().getB2cIdProducto().getEsCO())
                    .idSku((productResponse.getFields().getSku() == null) ? null : productResponse.getFields().getSku().getEsCO())
                    .idEntryId(productResponse.getEntryId())
                    .bottleType((productResponse.getFields().getB2cTipoDeBotella() == null) ? null : productResponse.getFields().getB2cTipoDeBotella().getEsCO())
                    .images(Collections.singletonList(productResponse.getFields().getImageProductDto().getFields().getFile().getEsCO().getUrl()))
                    .categoryPath(getCategory(productResponse))
                    .price(productsFromMiddleLayer.parallelStream().filter(productFromMiddleLayer -> productFromMiddleLayer.getSkuId().equals(productResponse.getFields().getSku().getEsCO())).findFirst().get().getPrice())
                    .stock(Double.valueOf(productsFromMiddleLayer.parallelStream().filter(productFromMiddleLayer -> productFromMiddleLayer.getSkuId().equals(productResponse.getFields().getSku().getEsCO())).findFirst().get().getInventary()))
                    .maximoAVender(productResponse.getFields().getMaximoAVender() == null ? 0 : productResponse.getFields().getMaximoAVender().getEsCO())
                    .iva(iva)
                    .ico(ico)
                    .isDigital(productResponse.getFields().getDigital() == null ? false : productResponse.getFields().getDigital().getEsCO())
                    .customTags((productResponse.getFields().getEtiquetasPersonalizadasB2c() == null) ? null : productResponse.getFields().getEtiquetasPersonalizadasB2c().getEsCO())
                    .build();
            productsToReturn.add(productList);
        });


        return productsToReturn;
    }

    private HashSet<String> addSkuProductsMiddleLayer(Iterable<IndexB2CSearch> productsFoundB2C) {
        HashSet<String> skusProductsMiddleLayer = new HashSet<>();
        productsFoundB2C.forEach(productB2CDto -> skusProductsMiddleLayer.add(productB2CDto.getFields().findValue("sku").findValue("es-CO").asText()));
        return skusProductsMiddleLayer;
    }

    private List<Map<String, String>> getCategory(DocumentProductDto productResponse) {
        List<Map<String, String>> categories = new ArrayList<>();
        if (productResponse.getFields().getCategoryPath() != null) {
            for (ProductCategory productCategory : productResponse.getFields().getCategoryPath()) {
                String entryId = productCategory.getEntryId();
                String name = (productCategory.getNameCat() != null) ? productCategory.getNameCat() : productCategory.getNameSubCat();

                Map<String, String> map = Map.of("entryId", entryId, "name", name);
                categories.add(map);
            }
        }
        return categories;
    }

    private List<ProductList> setFavoriteProducts(String token, List<ProductList> productsToReturn) {
        if (token == null || token.isEmpty() || token.isBlank()) {
            return productsToReturn;
        }
        String email = "";
        try {
            email = TokenUtil.getEmailFromToken(token);
        } catch (Exception e) {
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ProductConstants.DPA026, messagesUtil.getMessage(ProductConstants.DPA026));
        }

        JsonNode wishListByEmail = vtexProvider.getWishListByUserEmail(email);

        if (wishListByEmail.findPath(VTEXT_WISHLIST_LISTITEMS).isEmpty()) {
            log.info(messagesUtil.getMessage(ProductConstants.DPV002));
        }
        HashSet<String> wishListSkus = new HashSet<>();
        wishListByEmail.findPath(LIST_ITEMS)
                .elements()
                .forEachRemaining(node -> wishListSkus.add(node.findValue("Sku").asText()));
        productsToReturn.stream().parallel().forEachOrdered(product -> product.setIsFavorite(wishListSkus.parallelStream().anyMatch(wishSku -> wishSku.equals(product.getIdSku()))));
        return productsToReturn;
    }

}
