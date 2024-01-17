package io.quarkiverse.clerk.auth.deployment;

import io.quarkiverse.clerk.auth.runtime.ClerkClientProducer;
import io.quarkiverse.clerk.auth.runtime.authentication.ClerkSecurityAuthMechanism;
import io.quarkiverse.clerk.auth.runtime.authentication.DefaultClerkIdentityProvider;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

public class ClerkAuthBuildSteps {

    private static final String FEATURE = "clerk-auth";

    @BuildStep
    public FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    public AdditionalBeanBuildItem producer() {
        return new AdditionalBeanBuildItem(ClerkClientProducer.class);
    }

    @BuildStep
    public void setupClerk(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        AdditionalBeanBuildItem.Builder builder = AdditionalBeanBuildItem.builder().setUnremovable();

        builder.addBeanClasses(DefaultClerkIdentityProvider.class);
        additionalBeans.produce(builder.build());
    }

    @BuildStep
    public void setupAuth(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {

        AdditionalBeanBuildItem.Builder builder = AdditionalBeanBuildItem.builder().setUnremovable();

        builder.addBeanClasses(DefaultClerkIdentityProvider.class, ClerkSecurityAuthMechanism.class);
        additionalBeans.produce(builder.build());
    }
}
