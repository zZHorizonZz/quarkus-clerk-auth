package io.quarkiverse.clerk.auth.runtime.sdk.email;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkiverse.clerk.auth.runtime.sdk.user.IdentificationLink;
import io.quarkiverse.clerk.auth.runtime.sdk.verification.Verification;
import lombok.Data;

@Data
public class EmailAddress {
    private String id;
    private String object;

    @JsonProperty("email_address")
    private String emailAddress;
    private boolean reserved;
    private Verification verification;

    @JsonProperty("linked_to")
    private IdentificationLink[] linkedTo;
}
