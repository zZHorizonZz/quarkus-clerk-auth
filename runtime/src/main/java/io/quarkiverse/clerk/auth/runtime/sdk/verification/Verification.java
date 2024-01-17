package io.quarkiverse.clerk.auth.runtime.sdk.verification;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Verification {
    private String status;
    private String strategy;
    private int attempts;

    @JsonProperty("expire_at")
    private long expireAt;

    @JsonProperty("verified_at_client")
    private String verifiedAtClient;
    private String nonce;

    @JsonProperty("external_verification_redirect_url")
    private String externalVerificationRedirectUrl;
    private byte[] error;
}
