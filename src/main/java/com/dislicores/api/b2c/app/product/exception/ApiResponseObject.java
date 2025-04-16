package com.dislicores.api.b2c.app.product.exception;

import com.dislicores.api.b2c.app.product.domain.exception.FieldErrorDto;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class ApiResponseObject {

    private OffsetDateTime timestamp;
    private Integer status;
    private String code;
    private String message;
    private String path;
    private List<FieldErrorDto> fields;

}
