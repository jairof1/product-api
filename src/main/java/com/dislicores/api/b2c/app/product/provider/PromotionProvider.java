package com.dislicores.api.b2c.app.product.provider;

import com.dislicores.api.b2c.app.product.domain.promotions.LevelClubEnum;
import com.dislicores.api.b2c.app.product.domain.promotions.PromotionRequestGenericDto;
import com.dislicores.api.b2c.app.product.domain.promotions.PromotionSearchDto;
import com.dislicores.api.b2c.app.product.exception.ProductException;
import com.dislicores.api.b2c.app.product.util.MessagesUtil;
import com.dislicores.api.b2c.app.product.util.ProductConstants;
import com.dislicores.api.b2c.app.product.util.UtilProperties;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.dislicores.api.b2c.app.product.util.ProductConstants.FUNCTIONALITY;

@Slf4j
@Component
@RequiredArgsConstructor
public class PromotionProvider {

    private final RestTemplate client;

    private final UtilProperties utilProperties;

    private final MessagesUtil messagesUtil;

    public ResponseEntity<JsonNode> getPromotionsByListSku(PromotionRequestGenericDto requestPromotionsByListSku) {

        String url = utilProperties.getPromotions().getMachineUrl().concat(ProductConstants.PATH_PROMOTION_LIST_PRODUCTS);
        ResponseEntity<JsonNode> responseTemplate = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Ip-Address", FUNCTIONALITY);
            headers.set("x-api-key", utilProperties.getPromotions().getApiKey());
            HttpEntity<?> requestEntity = new HttpEntity<>(requestPromotionsByListSku, headers);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            responseTemplate = this.client.exchange(
                    builder.build().toUriString(),
                    HttpMethod.POST,
                    requestEntity,
                    JsonNode.class
            );

        } catch (HttpClientErrorException ex) {
            if( !ex.getStatusCode().is2xxSuccessful() ) {
                log.error(this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
                throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ProductConstants.DPR002, this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
            }

        } catch (Exception ex) {
            log.error(this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ProductConstants.DPR002, this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
        }
        return responseTemplate;
    }


    public ResponseEntity<JsonNode> getLevelClubSkus(LevelClubEnum levelClub) {
        String url = utilProperties.getPromotions().getMachineUrl().concat(ProductConstants.PATH_PROMOTION_CLUB_LEVEL).concat(levelClub.name());
        ResponseEntity<JsonNode> responseTemplate = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Ip-Address", FUNCTIONALITY);
            HttpEntity requestEntity = new HttpEntity<>(headers);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            responseTemplate = this.client.exchange(
                    builder.build().toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    JsonNode.class
            );

        } catch (HttpClientErrorException ex) {
            if( !ex.getStatusCode().is2xxSuccessful() ) {
                log.error(this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
                throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ProductConstants.DPR002, this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
            }

        } catch (Exception ex) {
            log.error(this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ProductConstants.DPR002, this.messagesUtil.getFormatedMessage(ProductConstants.DPR002, new Object[]{ex.getMessage()}));
        }
        return responseTemplate;
    }

    public JsonNode getPromotionsSku(PromotionSearchDto promotionSearchDto) {
        // String url = utilProperties.getPromotions().getMachineUrl().concat(ProductConstants.PATH_PROMOTION_SEARCH);
        String url = utilProperties.getPromotions().getMachineUrl().concat(ProductConstants.PATH_PROMOTION_SEARCH);
        var headers = new HttpHeaders();
        headers.add("Ip-Address", FUNCTIONALITY);
        headers.set("x-api-key", utilProperties.getPromotions().getApiKey());
        var request = new HttpEntity<>(promotionSearchDto, headers);
        try {
            var response = client.exchange(URI.create(url), HttpMethod.POST, request, JsonNode.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error consultando promocioens, request payload {} - status {}", promotionSearchDto, e.getStatusCode());
            return null;
        }
    }
}
