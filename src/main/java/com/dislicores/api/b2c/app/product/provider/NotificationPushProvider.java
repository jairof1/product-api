package com.dislicores.api.b2c.app.product.provider;


import java.net.URI;

import com.dislicores.api.b2c.app.product.domain.notification.NotificacionPushDto;
import com.dislicores.api.b2c.app.product.util.UtilProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationPushProvider {

    private final RestTemplate client;
    private final UtilProperties utilProperties;

    public void sendPushNotificacion(NotificacionPushDto notificacionPushDto) {
        log.debug("NotificacionPushProvider-sendPushNotificacion start");
        var headers = new HttpHeaders();
        headers.add("Ip-Address", "b2b-app-product");
        headers.add("X-API-Key", utilProperties.getNotification().getApiKey());
        HttpEntity<NotificacionPushDto> request = new HttpEntity<>(notificacionPushDto, headers);
        try {
            client.exchange(URI.create(utilProperties.getNotification().getPushUrl()), HttpMethod.POST, request, Void.class);
            log.debug("Notificacion push enviada con exito, request payload {}", notificacionPushDto);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error enviando notificacion push, request payload {} - status {}", notificacionPushDto, e.getStatusCode());
        }
        log.debug("NotificacionPushProvider-sendPushNotificacion end");
    }

    public void savePushNotificacion(NotificacionPushDto notificacionPushDto) {
        log.debug("NotificacionPushProvider-savePushNotificacion start");
        var headers = new HttpHeaders();
        headers.add("Ip-Address", "b2b-app-product");
        headers.add("X-API-Key", utilProperties.getNotification().getApiKey());
        HttpEntity<NotificacionPushDto> request = new HttpEntity<>(notificacionPushDto, headers);
        try {
            client.exchange(URI.create(utilProperties.getNotification().getSavePushUrl()), HttpMethod.POST, request, Void.class);
            log.debug("Notificacion push guardada con exito, request payload {}", notificacionPushDto);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error guardando notificacion push, request payload {} - status {}", notificacionPushDto, e.getStatusCode());
        }
        log.debug("NotificacionPushProvider-savePushNotificacion end");
    }
}
