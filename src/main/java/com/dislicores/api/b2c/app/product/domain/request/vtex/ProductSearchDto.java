package com.dislicores.api.b2c.app.product.domain.request.vtex;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 933036075329911401L;
    /**
     * Filter by full text.
     */
    private String ft;
    /**
     * General filter.
     * It can be by category (fq=C:/{a}/{b}),
     * by specification (fq=specificationFilter_{a}:{b}),
     * by price range (fq=P:[{a} TO {b}]),
     * by collection (fq=productClusterIds:{{productClusterId}}),
     * by product ID (fq=productId:{{productId}}),
     * by SKU ID (fq=skuId:{{skuId}}),
     * by Reference ID (fq=alternateIds_RefId:{{referenceId}}),
     * by EAN13 (fq=alternateIds_Ean:{{ean13}}),
     * by availability at a specific sales channel (fq=isAvailablePerSalesChannel_{{sc}}:{{bool}}),
     * by available at a specific seller (fq=sellerId:{{sellerId}})
     */
    private String fq;
    /**
     * Starter page range.
     * These parameters allow the API to be paginated.
     * Take into account that the initial and final pages cannot have a separation superior to 50 pages.
     * Thus, it will be displayed 50 items per page.
     */
    private Integer _from;
    /**
     * Finisher page range.
     * These parameters allow the API to be paginated.
     * Take into account that the initial and final pages cannot have a separation superior to 50 pages.
     * Thus, it will be displayed 50 items per page.
     */
    private Integer _to;
    /**
     * Sorting method.
     * It can be by Price (O=OrderByPriceDESC or O=OrderByPriceASC),
     * by Top Selling Products (O=OrderByTopSaleDESC),
     * by Best Reviews (O=OrderByReviewRateDESC),
     * by Name (O=OrderByNameASC or O=OrderByNameDESC),
     * by Release Date (O=OrderByReleaseDateDESC),
     * by Best Discounts (O=OrderByBestDiscountDESC),
     * by Score (O=OrderByScoreDESC)
     */
//    @JsonProperty(value = "O")
//    @SerializedName("O")
//    @JsonAlias(value = "O")
    private String O;

    public ProductSearchDto byCategory(List<String> categories) {
        this.fq = "C:" + categories.stream().map("/%s"::formatted).collect(Collectors.joining());
        return this;
    }

    public ProductSearchDto byBrand(List<String> brands) {
        this.fq = "B:" + brands.stream().map("/%s"::formatted).collect(Collectors.joining());
        return this;
    }

    public ProductSearchDto bySpecificationFilter(String field, String value) {
        this.fq = "specificationFilter_%s:%s".formatted(field, value);
        return this;
    }

    public ProductSearchDto byPriceRange(String start, String end) {
        this.fq = "P:[%s TO %s]".formatted(start, end);
        return this;
    }

    public ProductSearchDto byCollection(List<String> clusterIds) {
        this.fq = "productClusterIds:" + String.join(",", clusterIds);
        return this;
    }

    public ProductSearchDto byProductId(String id) {
        this.fq = "productId:%s".formatted(id);
        return this;
    }

    public ProductSearchDto bySku(String sku) {
        this.fq = "skuId:%s".formatted(sku);
        return this;
    }

    public ProductSearchDto byAlternateIds(String field, String value) {
        this.fq = "alternateIds_%s:%s".formatted(field, value);
        return this;
    }

    public ProductSearchDto byAvailabilitySalesChannel(String channel, Boolean flag) {
        this.fq = "isAvailablePerSalesChannel_%s:%s".formatted(channel, flag ? "1" : "0");
        return this;
    }

    public ProductSearchDto bySellerId(String id) {
        this.fq = "sellerId:%s".formatted(id);
        return this;
    }

    public ProductSearchDto orderBy(String field, Boolean isAsc) {
        this.O = "OrderBy%s%s".formatted(field, isAsc ? "ASC" : "DESC");
        return this;
    }
}
