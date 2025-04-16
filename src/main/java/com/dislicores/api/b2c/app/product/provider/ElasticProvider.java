package com.dislicores.api.b2c.app.product.provider;

import com.dislicores.api.b2c.app.product.exception.ProductException;
import com.dislicores.api.b2c.app.product.util.MessagesUtil;
import com.dislicores.api.b2c.app.product.util.ProductConstants;
import com.dislicores.api.b2c.app.product.util.UtilProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticProvider {

    private final RestTemplate client;
    private final UtilProperties utilProperties;
    private final MessagesUtil messagesUtil;

    public ResponseEntity<JsonNode> sendFullQuery(JsonNode fullQuery) {
        log.debug("Init sendFullQuery {}", fullQuery.asText());
        String url = utilProperties.getElasticSearch().getUrlSearchDocuments();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", utilProperties.getElasticSearch().getBasic());
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(fullQuery, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        try {
            ResponseEntity<JsonNode> responseTemplate = this.client.exchange(
                    builder.build().toUriString(),
                    HttpMethod.POST,
                    entity,
                    JsonNode.class
            );
            log.debug("Finish sendFullQuery");
            return responseTemplate;
        } catch (Exception e) {
            log.error("Error con la consulta cognito, {}", this.messagesUtil.getMessage(ProductConstants.DPA028));
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ProductConstants.DPA028 , this.messagesUtil.getMessage(ProductConstants.DPA028));
        }
    }
}
