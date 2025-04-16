package com.dislicores.api.b2c.app.product.config.elastic;

import com.dislicores.api.b2c.app.product.model.search.IndexB2CSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;
import java.util.Optional;

public class ElasticConverterB2C implements Converter<Map<String, Object>, IndexB2CSearch> {
    @Override
    public IndexB2CSearch convert(Map<String, Object> source) {
        return IndexB2CSearch
                .builder()
                .entryId(source.get("entryId").toString())
                .contentType(Optional.ofNullable(source.get("contentType")).map(Object::toString).orElse(""))
                .environment(source.get("environment").toString())
                .fields(new ObjectMapper().valueToTree(source.get("fields")))
                .build();
    }
}
