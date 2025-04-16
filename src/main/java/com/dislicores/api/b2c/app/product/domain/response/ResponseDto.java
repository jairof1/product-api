package com.dislicores.api.b2c.app.product.domain.response;

import com.dislicores.api.b2c.app.product.domain.exception.FieldErrorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase que define un objeto de respuesta generico para todos las Apis
 *
 * @author ricardo.ayala@pragma.com.co
 *
 * @param <T>
 */
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private int status;
    private String responseCode;
    private String responseMessage;
    private List<FieldErrorDto> fieldErrors;
    private T data;
    @JsonIgnore
    private String user;

    public ResponseDto(int status, String responseCode, String responseMessage, List<FieldErrorDto> fieldErrors, T data) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.fieldErrors = fieldErrors;
        this.data = data;
    }

    public ResponseDto(int status, String responseCode, String responseMessage, List<FieldErrorDto> fieldErrors) {
        this(status, responseCode, responseMessage, fieldErrors, null);
    }

    public ResponseDto(int status, String responseCode, String responseMessage, T data) {
        this(status, responseCode, responseMessage, null, data);
    }

    public ResponseDto(int status, String responseCode, String responseMessage) {
        this(status, responseCode, responseMessage, null, null);

    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @return the responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @return the fieldErrors
     */
    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResponseDto{" + "status=" + status + ", responseCode='" + responseCode + '\'' + ", responseMessage='" + responseMessage
                + '\'' + ", fieldErrors=" + fieldErrors + ", data=" + data + '}';
    }

}
