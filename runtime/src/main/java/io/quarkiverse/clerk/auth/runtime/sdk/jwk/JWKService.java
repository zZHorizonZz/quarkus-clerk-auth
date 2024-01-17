package io.quarkiverse.clerk.auth.runtime.sdk.jwk;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.jose4j.jwk.JsonWebKeySet;

import io.quarkiverse.clerk.auth.runtime.sdk.BaseService;
import io.quarkiverse.clerk.auth.runtime.sdk.Client;

public class JWKService extends BaseService {

    public JWKService(Client client) {
        super(client);
    }

    public JsonWebKeySet listAll() throws Exception {
        HttpRequest request = newRequest("GET", "jwks", null);

        HttpResponse<String> response = getHttpClient().send(request, BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to fetch JWKS: HTTP error code " + response.statusCode());
        }

        return new JsonWebKeySet(response.body());
    }
}
