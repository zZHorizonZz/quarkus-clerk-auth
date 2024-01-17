package io.quarkiverse.clerk.auth.runtime;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "clerk.auth", phase = ConfigPhase.RUN_TIME)
public class ClerkClientConfiguration {

    /**
     * Enable or disable Clerk authentication.
     */
    @ConfigItem
    public Optional<String> token;
}
