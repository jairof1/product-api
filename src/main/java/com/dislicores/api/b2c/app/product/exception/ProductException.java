package com.dislicores.api.b2c.app.product.exception;

import com.dislicores.api.b2c.app.product.domain.exception.FieldErrorDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Excepcion personalizada para el arquetipo de Dislicores, se adicionan atributos que apoyen el manejo de excepciones.
 */
@Getter
public class ProductException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -958060418553191973L;

    /**
     * Valor que captura la fecha y hora de la excepcion
     */
    private OffsetDateTime timestamp;

    /**
     * Valor que permite definir un estado para la respuesta HTTP en caso de que deba ser propagada desde el origen de la excepcion
     */
    private final int status;
    /**
     * Codigo unico que identifique la respuesta para el presente microservicio
     */
    private final String code;
    /**
     * Conjunto de campos con errores por obligatoriedad o formato
     */
    private final List<FieldErrorDto> fieldErrors;
    /**
     * Mensaje de complemento para identificar error
     */
    private String message;
    /**
     * Rura del endpoint afectado
     */
    private String path;

    /**
     * Constructor para la creacion de excepciones personalizadas sin causa raiz
     *
     * @param status
     * @param code
     * @param message
     */
    public ProductException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
        this.fieldErrors = null;
    }

    /**
     * Constructor para la creacion de excepciones personalizadas
     *
     * @param status
     *            Estado HTTP
     * @param code
     *            Codigo de respuesta unico
     * @param message
     *            Mensaje con el detalle del error
     * @param e
     *            Objeto con la informacion de la cauza raiz de la excepcion
     */
    public ProductException(int status, String code, String message, Throwable e) {
        super(message, e);
        this.status = status;
        this.code = code;
        this.fieldErrors = null;
    }

    /**
     * Constructor para la creacion de excepciones con una lista de campos erroneos
     * 
     * @param status
     * @param code
     * @param message
     * @param fieldErrors
     */
    public ProductException(int status, String code, String message, List<FieldErrorDto> fieldErrors) {
        super(message);
        this.status = status;
        this.message = message;
        this.code = code;
        this.fieldErrors = fieldErrors;
    }
    /**
     * Constructor para la creacion de excepciones por ausencia de params
     *
     * @param status
     * @param code
     * @param message
     * todo
     */
    public ProductException(int status, String code, String message, HttpStatus httpStatus, String path) {
        super(message);
        this.message = message;
        this.status = status;
        this.code = code;
        this.fieldErrors = null;
        this.path = path;
    }

}
