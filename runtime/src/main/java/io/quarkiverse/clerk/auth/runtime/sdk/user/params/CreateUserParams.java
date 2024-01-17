package io.quarkiverse.clerk.auth.runtime.sdk.user.params;

import lombok.Data;

@Data
public class CreateUserParams {
    private String[] emailAddresses;
    private String[] phoneNumbers;
    private String[] web3Wallets;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String externalID;
    private String unsafeMetadata;
    private String publicMetadata;
    private String privateMetadata;
    private String passwordDigest;
    private String passwordHasher;
    private boolean skipPasswordRequirement;
    private boolean skipPasswordChecks;
    private String totpSecret;
    private String[] backupCodes;
    private String createdAt;
}
