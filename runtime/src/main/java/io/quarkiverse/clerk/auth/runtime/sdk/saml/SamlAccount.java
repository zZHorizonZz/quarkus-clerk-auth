package io.quarkiverse.clerk.auth.runtime.sdk.saml;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkiverse.clerk.auth.runtime.sdk.verification.Verification;
import lombok.Data;

@Data
public class SamlAccount {
    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("provider_user_id")
    private String providerUserId;
    @JsonProperty("verification")
    private Verification verification;
}
