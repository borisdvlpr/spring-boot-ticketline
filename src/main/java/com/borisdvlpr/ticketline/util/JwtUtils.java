package com.borisdvlpr.ticketline.util;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public final class JwtUtils {
    private JwtUtils() { }

    public static UUID parseUserId(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }
}
