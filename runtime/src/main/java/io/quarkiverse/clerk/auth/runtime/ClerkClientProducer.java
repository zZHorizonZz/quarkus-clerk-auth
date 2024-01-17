package io.quarkiverse.clerk.auth.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import io.github.zzhorizonzz.sdk.ClerkClient;

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

        return new ClerkClient(configuration.token.get());
    }
}
