package io.quarkiverse.clerk.auth.runtime.authentication;

import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jose4j.jwt.MalformedClaimException;

import io.github.zzhorizonzz.sdk.session.SessionClaims;

public class ClerkPrincipal implements JsonWebToken {

    private final SessionClaims sessionClaims;
    private final Map<String, Object> claims;

    public ClerkPrincipal(SessionClaims claims) {
        this.sessionClaims = claims;
        this.claims = claims.getClaims().getClaimsMap();
    }

    @Override
    public String getName() {
        try {
            return sessionClaims.getClaims().getSubject();
        } catch (MalformedClaimException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> getClaimNames() {
        return claims.keySet();
    }

    @Override
    public <T> T getClaim(String claimName) {
        return (T) claims.get(claimName);
    }

    public SessionClaims getSessionClaims() {
        return sessionClaims;
    }
}
