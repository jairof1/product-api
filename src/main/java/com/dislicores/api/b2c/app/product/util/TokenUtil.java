package com.dislicores.api.b2c.app.product.util;

import com.auth0.jwt.JWT;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenUtil {

    public static String getEmailFromToken(String token) {
     return JWT.decode(token)
                .getClaim("email")
                .asString();
    }
}