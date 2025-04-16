package com.dislicores.api.b2c.app.product.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public class FieldErrorDto implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5525627609074684645L;
    
    private String field;
    private String message;

}
