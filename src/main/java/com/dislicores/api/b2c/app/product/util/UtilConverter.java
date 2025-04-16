package com.dislicores.api.b2c.app.product.util;

import com.dislicores.api.b2c.app.product.domain.promotions.DocumentTypeEnum;
import com.dislicores.api.b2c.app.product.domain.promotions.LevelClubEnum;
import com.dislicores.api.b2c.app.product.domain.promotions.RequestPromotionDto;
import com.dislicores.api.b2c.app.product.exception.ProductException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * Clase de utilidad para convertir JsonNodes a Clases de Java
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toJavaClass(JsonNode jsonNode, Class<T> valueType, MessagesUtil messagesUtil) {
        try {
            return objectMapper.treeToValue(jsonNode, valueType);
        } catch (JsonProcessingException e) {
            log.error("error convirtiendo JSON a POJO... {}", e.getLocalizedMessage());
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ProductConstants.DPA000, messagesUtil.getMessage(ProductConstants.DPA000));
        }
    }

    public static RequestPromotionDto getRequestPromotionDto(String documentNumber, DocumentTypeEnum documentType, LevelClubEnum levelClub) {
        var promotionDtoBuilder = RequestPromotionDto.builder();
        promotionDtoBuilder.numId(documentNumber);
        if (StringUtils.isBlank(documentNumber)) {
            promotionDtoBuilder.numId("");
        }
        if (documentType != null) {
            promotionDtoBuilder.typeId(documentType.name());
        }
        if (levelClub != null) {
            promotionDtoBuilder.levelClub(levelClub.name());
        }
        return promotionDtoBuilder.build();
    }

    public static Double validNullValue(Double value) {
        return value == null ? 0 : value;
    }

    public static ObjectMapper mapper() {
        return JsonMapper.builder().addModule(new JavaTimeModule()).build();
    }

    public static <T> T convertJsonToClass(String json, TypeReference<T> reference) {
        try {
            return mapper().readValue(json, reference);
        } catch (Exception ignored) {
            return null;
        }
    }
}