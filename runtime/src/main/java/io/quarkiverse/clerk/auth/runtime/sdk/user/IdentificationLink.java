package io.quarkiverse.clerk.auth.runtime.sdk.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class IdentificationLink {
    @JsonProperty("type")
    private String identType;

    @JsonProperty("id")
    private String identId;
}
