package com.dislicores.api.b2c.app.product.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class NotificacionPushDto {
    private String notificationTemplate;
    private String email;
    private Map<String, String> payload;
}
