package com.dislicores.api.b2c.app.product.provider;

import com.dislicores.api.b2c.app.product.domain.request.vtex.ProductSearchDto;
import com.dislicores.api.b2c.app.product.model.vtex.ListRoot;
import com.dislicores.api.b2c.app.product.util.ProductConstants;
import com.dislicores.api.b2c.app.product.exception.ProductException;
import com.dislicores.api.b2c.app.product.util.MessagesUtil;
import com.dislicores.api.b2c.app.product.model.properties.VtexProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.dislicores.api.b2c.app.product.util.ProductConstants.VTEXT_WISHLIST_FIELDS;
import static com.dislicores.api.b2c.app.product.util.ProductConstants.VTEXT_WISHLIST_SCHEMA;

@Component
@RequiredArgsConstructor
@Slf4j
public class VtexProvider {

    private final RestTemplate client;
    private final MessagesUtil messagesUtil;
    private final VtexProperties properties;

    public JsonNode getWishListByUserEmail(String userEmail) {
        var response = client.exchange(setFinalUrlWishList(userEmail), HttpMethod.GET, new HttpEntity<>(getVtexHeaders()), JsonNode.class);
        validResponse(response);

        return response.getBody();
    }

    public void patchWishList(ListRoot root) {
        var response = client.exchange(properties.getEndpointSaveWishlist(), HttpMethod.PATCH, new HttpEntity<>(root, getVtexHeaders()), JsonNode.class);
        validResponse(response);
    }

    public void deleteWishListById(String documentId) {
        var response = client.exchange(properties.getEndpointDeleteWishlist(), HttpMethod.DELETE, new HttpEntity<>(getVtexHeaders()), JsonNode.class, documentId);
        validResponse(response);
    }

    private void validResponse(ResponseEntity<?> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new ProductException(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), ProductConstants.DPA000, messagesUtil.getMessage(ProductConstants.DPA000)
            );
        }
    }

    private String setFinalUrlWishList(String userEmail) {
        return UriComponentsBuilder.fromHttpUrl(properties.getEndpointGetWishlist())
                .queryParam("_fields", VTEXT_WISHLIST_FIELDS)
                .queryParam("_schema", VTEXT_WISHLIST_SCHEMA)
                .queryParam("email", userEmail)
                .build().toString();
    }

    private HttpHeaders getVtexHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("X-VTEX-API-AppKey", properties.getVtexApiKey());
        headers.set("X-VTEX-API-AppToken", properties.getVtexApiToken());

        return headers;
    }


    public ResponseEntity<JsonNode> getSearchProduct(ProductSearchDto model) {
        ResponseEntity<JsonNode> responseTemplate=null;
        try{
            responseTemplate  = client.exchange( setFinalUrlProductSearch(model) , HttpMethod.GET, new HttpEntity<>(getVtexHeaders()),  JsonNode.class);
        }
        catch (HttpClientErrorException ex) {
                throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ProductConstants.DPA016 , this.messagesUtil.getMessage(ProductConstants.DPA016));
        }
        return  responseTemplate;
    }

    private String setFinalUrlProductSearch(ProductSearchDto model) {
        return UriComponentsBuilder.fromHttpUrl(properties.getEndpointGetProductSearch())
                .queryParam("_from", model.get_from())
                .queryParam("_to", model.get_to())
                .queryParam("fq", model.getFq())
                .build().toString();
    }

}