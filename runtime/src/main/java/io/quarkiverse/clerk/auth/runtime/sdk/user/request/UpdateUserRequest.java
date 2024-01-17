package io.quarkiverse.clerk.auth.runtime.sdk.user.request;

import io.quarkiverse.clerk.auth.runtime.sdk.user.Metadata;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String primaryEmailAddressID;
    private String primaryPhoneNumberID;
    private String primaryWeb3WalletID;
    private String username;
    private String profileImageID;
    private String profileImage;
    private String password;
    private boolean skipPasswordChecks;
    private boolean signOutOfOtherSessions;
    private String externalID;
    private Metadata publicMetadata;
    private Metadata privateMetadata;
    private Metadata unsafeMetadata;
    private String totpSecret;
    private String[] backupCodes;
    private String createdAt;
}
