package io.quarkiverse.clerk.auth.runtime.authentication;

import io.quarkus.security.identity.request.BaseAuthenticationRequest;

public class ClerkAuthenticationRequest extends BaseAuthenticationRequest {

    private final String token;

    public ClerkAuthenticationRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
