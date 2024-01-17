package io.quarkiverse.clerk.auth.runtime.sdk;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

import io.quarkiverse.clerk.auth.runtime.sdk.jwk.JWKCache;
import io.quarkiverse.clerk.auth.runtime.sdk.jwk.JWKService;
import io.quarkiverse.clerk.auth.runtime.sdk.jwt.JWTService;
import io.quarkiverse.clerk.auth.runtime.sdk.jwt.VerifyTokenOptions;
import io.quarkiverse.clerk.auth.runtime.sdk.session.SessionClaims;
import io.quarkiverse.clerk.auth.runtime.sdk.user.UserService;
import lombok.Getter;

@Getter
public class Client {
    public static final String PRODUCTION_URL = "https://api.clerk.dev/v1/";

    private final String token;
    private final String baseUrl = PRODUCTION_URL;
    private final JWKCache jwksCache;
    private final JWKService jwksService;
    private final UserService userService;

    public Client(String token) {
        this.token = token;
        this.jwksCache = new JWKCache();
        this.jwksService = new JWKService(this);
        this.userService = new UserService(this);
    }

    public SessionClaims verifyToken(String token) throws Exception {
        JWTService jwtService = new JWTService(this);
        return jwtService.verifyToken(token, new VerifyTokenOptions());
    }

    public JsonWebKey getJWK(String kid) throws Exception {
        if (jwksCache.isInvalid()) {
            JsonWebKeySet jwks = jwksService.listAll();
            jwksCache.set(jwks);
        }

        return jwksCache.get(kid);
    }
}
