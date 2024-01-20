package io.quarkiverse.clerk.auth.runtime.http;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.impl.WebClientBase;

public class TokenClient extends WebClientBase {

    public TokenClient(Vertx vertx, WebClientOptions options, String apiToken) {
        super(vertx.createHttpClient(options), options);
        this.addInterceptor(new TokenInterceptor(apiToken));
    }
}
