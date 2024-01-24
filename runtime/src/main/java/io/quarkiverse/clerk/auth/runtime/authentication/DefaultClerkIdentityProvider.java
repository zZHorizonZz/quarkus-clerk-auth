package io.quarkiverse.clerk.auth.runtime.authentication;

import java.security.Principal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import io.github.zzhorizonzz.sdk.ClerkClient;
import io.github.zzhorizonzz.sdk.session.SessionClaims;
import io.quarkus.runtime.configuration.ProfileManager;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class DefaultClerkIdentityProvider implements IdentityProvider<ClerkAuthenticationRequest> {

    @Inject
    ClerkClient client;

    @Inject
    Logger log;

    @Override
    public Class<ClerkAuthenticationRequest> getRequestType() {
        return ClerkAuthenticationRequest.class;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(ClerkAuthenticationRequest request, AuthenticationRequestContext context) {
        return Uni.createFrom()
                .item(request.getToken())
                .onItem().transformToUni(idToken -> {
                    try {
                        SessionClaims claims = client.verifyToken(request.getToken());
                        if (claims == null) {
                            if (ProfileManager.getLaunchMode().isDevOrTest()) {
                                log.warnv("Invalid token: {0}", request.getToken());
                            }

                            return Uni.createFrom().failure(new UnauthorizedException("Invalid token"));
                        }

                        return Uni.createFrom().item(authenticate(claims));
                    } catch (Exception e) {
                        if (ProfileManager.getLaunchMode().isDevOrTest()) {
                            log.warnv("There was an error verifying the token: {0}", e.getMessage());
                        }

                        return Uni.createFrom().failure(new UnauthorizedException(e));
                    }
                });
    }

    public static SecurityIdentity authenticate(SessionClaims claims) {
        var builder = QuarkusSecurityIdentity.builder()
                .setPrincipal(getPrincipal(claims));

        return builder.build();
    }

    public static Principal getPrincipal(SessionClaims claims) {
        return new ClerkPrincipal(claims);
    }
}
