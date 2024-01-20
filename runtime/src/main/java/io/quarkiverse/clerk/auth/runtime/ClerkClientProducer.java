package io.quarkiverse.clerk.auth.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import com.microsoft.kiota.RequestAdapter;

import io.github.zzhorizonzz.sdk.ClerkClient;
import io.kiota.http.vertx.VertXRequestAdapter;
import io.quarkiverse.clerk.auth.runtime.http.TokenClient;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

@ApplicationScoped
public class ClerkClientProducer {

    @Inject
    ClerkClientConfiguration configuration;

    @Produces
    @Singleton
    @Default
    public ClerkClient getClerkClient() {
        if (configuration.token.isEmpty()) {
            throw new RuntimeException("Clerk token is not configured");
        }

        WebClientOptions webClientOptions = new WebClientOptions();
        WebClient webClient = new TokenClient(Vertx.vertx(), webClientOptions, configuration.token.get());
        RequestAdapter adapter = new VertXRequestAdapter(webClient);

        return new ClerkClient(adapter);
    }
}
