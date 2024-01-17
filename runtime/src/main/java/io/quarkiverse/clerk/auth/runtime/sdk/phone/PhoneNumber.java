package io.quarkiverse.clerk.auth.runtime.sdk.phone;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkiverse.clerk.auth.runtime.sdk.user.IdentificationLink;
import io.quarkiverse.clerk.auth.runtime.sdk.verification.Verification;
import lombok.Data;

@Data
public class PhoneNumber {
    private String id;
    private String object;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("reserved_for_second_factor")
    private boolean reservedForSecondFactor;

    @JsonProperty("default_second_factor")
    private boolean defaultSecondFactor;
    private boolean reserved;
    private Verification verification;

    @JsonProperty("linked_to")
    private IdentificationLink[] linkedTo;

    @JsonProperty("backup_codes")
    private String[] backupCodes;
}
