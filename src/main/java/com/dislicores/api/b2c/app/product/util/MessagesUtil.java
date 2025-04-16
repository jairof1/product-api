package com.dislicores.api.b2c.app.product.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@Slf4j
@Component
public class MessagesUtil {
    /** Ubicacion y nombre del archivo de propiedades */
    private static final String MESSAGE_PROPERTIES = "messages/messages";

    /**
     * Permite obtener un mensaje mediante el codigo que lo identifica
     *
     * @param code
     *            Codigo que identifica el mensaje
     * @return Mensaje
     */
    public String getMessage(String code) {
        var message = "";
        try {
            var bundle = ResourceBundle.getBundle(MESSAGE_PROPERTIES);
            message = bundle.getString(code);
        } catch (Exception e) {
            log.error("Error loading message property: {}", code);
        }
        return message;
    }

    /**
     * Permite obtener un mensaje mediante el codigo que lo identifica, reemplazando además los parámetros enviados
     *
     * @param code
     *            Codigo que identifica el mensaje
     * @param params
     *            Valores a reemplazar en el mensaje
     * @return Mensaje
     */
    public String getFormatedMessage(String code, Object[] params) {
        var message = "";
        try {
            var bundle = ResourceBundle.getBundle(MESSAGE_PROPERTIES);
            message = MessageFormat.format(bundle.getString(code), params);
        } catch (Exception e) {
            log.error("Error loading message property: {}", code);
        }
        return message;
    }

}
