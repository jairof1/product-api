package com.dislicores.api.b2c.app.product.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "util-properties")
public class UtilProperties {
    private String pathJsonFirebase;
    private String collectionFirebase;

    /** Objeto con la informacion para la conexion a elasticsearch */
    private ElasticSearch elasticSearch;

    private Promotions promotions;

    private Notification notification;

    @Getter
    @Setter
    public static class ElasticSearch {

        /** url para acceder a elasticsearch */
        private String url;
        /** Usuario para acceder a elasticsearch */
        private String user;
        /** Contraseña para acceder a elasticsearch */
        private String password;
        /** Credenciales basic para conexion http */
        private String basic;
        /** URL para realizar solicitudes http */
        private String urlSearchDocuments;

    }

    @Getter
    @Setter
    public static class Promotions {
        /** URL  */
        private String machineUrl;
        private String machineUrlPromotion;
        private String apiKey;
    }

    @Getter
    @Setter
    public static class Notification {
        /** URL  */
        private String machineUrl;
        private String pushUrl;
        private String savePushUrl;
        private String apiKey;
    }
}
