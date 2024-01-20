package io.quarkiverse.clerk.auth.runtime.http;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.client.impl.HttpContext;

public class TokenInterceptor implements Handler<HttpContext<?>> {

    private final String apiToken;

    public TokenInterceptor(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public void handle(HttpContext<?> httpContext) {
        httpContext.request().headers().set(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken);
        httpContext.next();
    }
}
