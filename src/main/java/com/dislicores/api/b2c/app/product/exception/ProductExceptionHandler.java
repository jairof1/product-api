package com.dislicores.api.b2c.app.product.exception;

import com.dislicores.api.b2c.app.product.domain.exception.FieldErrorDto;
import com.dislicores.api.b2c.app.product.util.ProductConstants;
import com.dislicores.api.b2c.app.product.domain.response.ResponseDto;
import com.dislicores.api.b2c.app.product.util.MessagesUtil;
import com.dislicores.api.b2c.app.product.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ProductExceptionHandler {

    private final MessagesUtil messagesUtil;
    private final ValidationUtils validationUtils;


    @ExceptionHandler({ProductException.class})
    public ResponseEntity<ApiResponseObject> handleProductException(ProductException e) {
        ApiResponseObject response = ApiResponseObject.builder()
                .timestamp(OffsetDateTime.now())
                .status(e.getStatus())
                .code(e.getCode())
                .message(e.getMessage())
                .fields(e.getFieldErrors())
                .path(e.getPath()).build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseDto<Void>> handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        log.debug("Handling MissingRequestHeaderException");
        var fieldError = new FieldErrorDto(e.getHeaderName(), this.messagesUtil.getMessage(ProductConstants.DPA004));
        ResponseDto<Void> response = new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPA001,
                this.messagesUtil.getMessage(ProductConstants.DPA001), List.of(fieldError));
        return new ResponseEntity<ResponseDto<Void>>(response, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Void>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.debug("Handling MethodArgumentNotValidException");
        var response = this.validationUtils.createResponseFromFieldErrors(e.getFieldErrors());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseDto<Void>> handleMissingServletRequestParameterException(
            final MissingServletRequestParameterException e) {
        log.debug("Handling MissingServletRequestParameterException");
        var fieldError = new FieldErrorDto(e.getParameterName(), this.messagesUtil.getMessage(ProductConstants.DPA002));
        ResponseDto<Void> response = new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), ProductConstants.DPA001,
                this.messagesUtil.getMessage(ProductConstants.DPA001), List.of(fieldError));
        return new ResponseEntity<ResponseDto<Void>>(response, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

}
