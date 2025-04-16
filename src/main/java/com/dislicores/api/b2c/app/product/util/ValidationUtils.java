package com.dislicores.api.b2c.app.product.util;

import com.dislicores.api.b2c.app.product.domain.exception.FieldErrorDto;
import com.dislicores.api.b2c.app.product.domain.response.ResponseDto;
import com.dislicores.api.b2c.app.product.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ValidationUtils {

    private final MessagesUtil messagesUtil;
    private final Validator validator;


    /**
     * Ejecuta la validacion de Beans con Hibernate Validation y lanza una excepcion con las violaciones encontradas
     *
     * @param o
     *            Objeto a validar
     * @throws ProductException
     *             En caso de incumplir alguna de las validaciones
     */
    public void validate(Object o) throws ProductException {
        var violations = this.validator.validate(o);
        this.throwExceptionIfExistsViolations(violations);
    }

    /**
     * Metodo que valida si existe alguna violacion, y de ser el caso lanza la excepcion correspondiente
     *
     * @param violations
     *            Lista con el resultado de la validacion
     */
    private void throwExceptionIfExistsViolations(Set<ConstraintViolation<Object>> violations) {
        if (!violations.isEmpty()) {
            throw new ProductException(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPA001,
                    this.messagesUtil.getMessage(ProductConstants.DPA001), violations.stream().map(this::toFieldError).toList());
        }
    }

    /**
     * Transforma una violacion hacia el Dto que permite visualizar el campo en el cual se incumple la validacion y el mensaje
     * correspondiente
     *
     * @param violation
     *            Objeto con la informacion de la violacion
     * @return DTO para los errores en campos
     */
    public FieldErrorDto toFieldError(ConstraintViolation<?> violation) {
        String code = violation.getMessage();
        String message = this.messagesUtil.getMessage(code);
        var propertyPath = violation.getPropertyPath().toString();
        return new FieldErrorDto(propertyPath.substring(propertyPath.lastIndexOf('.') + 1), message);
    }

    public FieldErrorDto toFieldError(FieldError error) {
        String code = error.getDefaultMessage();
        String message;
        message = this.messagesUtil.getMessage(code);
        var propertyPath = error.getField();
        return new FieldErrorDto(propertyPath, message);
    }

    public ResponseDto<Void> createResponseFromFieldErrors(List<FieldError> errors) {
        return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPA001,
                this.messagesUtil.getMessage(ProductConstants.DPA001),
                errors.stream().map(this::toFieldError).collect(Collectors.toList()));
    }

}
