package io.quarkiverse.clerk.auth.runtime.sdk.jwt;

import java.util.Map;

import org.eclipse.microprofile.jwt.JsonWebToken;

public class TokenClaims {
    private JsonWebToken standardClaims;
    private Map<String, Object> extra;

    public TokenClaims(JsonWebToken standardClaims, Map<String, Object> extra) {
        this.standardClaims = standardClaims;
        this.extra = extra;
    }

    public JsonWebToken getStandardClaims() {
        return standardClaims;
    }

    public void setStandardClaims(JsonWebToken standardClaims) {
        this.standardClaims = standardClaims;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }
}
