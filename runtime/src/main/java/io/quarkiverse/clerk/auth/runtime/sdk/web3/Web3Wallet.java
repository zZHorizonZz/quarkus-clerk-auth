package io.quarkiverse.clerk.auth.runtime.sdk.web3;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkiverse.clerk.auth.runtime.sdk.verification.Verification;
import lombok.Data;

@Data
public class Web3Wallet {
    private String id;
    private String object;

    @JsonProperty("web3_wallet")
    private String web3Wallet;
    private Verification verification;
}
