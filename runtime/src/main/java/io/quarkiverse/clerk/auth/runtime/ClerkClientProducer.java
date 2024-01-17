package io.quarkiverse.clerk.auth.runtime;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import io.quarkiverse.clerk.auth.runtime.sdk.Client;

@ApplicationScoped
public class ClerkClientProducer {

    @Inject
    ClerkClientConfiguration configuration;

    @Produces
    @Singleton
    @Default
    public Client getClerkClient() {
        if (configuration.token.isEmpty()) {
            throw new RuntimeException("Clerk token is not configured");
        }

        return new Client(configuration.token.get());
    }
}
