package io.quarkiverse.clerk.auth.runtime.sdk.user;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkiverse.clerk.auth.runtime.sdk.email.EmailAddress;
import io.quarkiverse.clerk.auth.runtime.sdk.phone.PhoneNumber;
import io.quarkiverse.clerk.auth.runtime.sdk.saml.SamlAccount;
import io.quarkiverse.clerk.auth.runtime.sdk.web3.Web3Wallet;
import lombok.Data;

@Data
public class User {
    private String id;
    private String object;
    @JsonProperty("external_id")
    private String externalID;
    @JsonProperty("primary_email_address_id")
    private String primaryEmailAddressID;

    @JsonProperty("primary_phone_number_id")
    private String primaryPhoneNumberID;

    @JsonProperty("primary_web3_wallet_id")
    private String primaryWeb3WalletID;
    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("profile_image_url")
    private String profileImageURL;

    @JsonProperty("image_url")
    private String imageURL;
    @JsonProperty("has_image")
    private boolean hasImage;
    @JsonProperty("public_metadata")
    private Map<String, Object> publicMetadata;

    @JsonProperty("private_metadata")
    private Map<String, Object> privateMetadata;

    @JsonProperty("unsafe_metadata")
    private Map<String, Object> unsafeMetadata;

    private String gender;
    private String birthday;

    @JsonProperty("email_addresses")
    private EmailAddress[] emailAddresses;

    @JsonProperty("phone_numbers")
    private PhoneNumber[] phoneNumbers;

    @JsonProperty("web3_wallets")
    private Web3Wallet[] web3Wallets;

    @JsonProperty("password_enabled")
    private boolean passwordEnabled;

    @JsonProperty("two_factor_enabled")
    private boolean twoFactorEnabled;

    @JsonProperty("totp_enabled")
    private boolean totpEnabled;

    @JsonProperty("backup_code_enabled")
    private boolean backupCodeEnabled;

    @JsonProperty("external_accounts")
    private List<Map<String, Object>> externalAccounts;

    @JsonProperty("saml_accounts")
    private List<SamlAccount> samlAccounts;

    @JsonProperty("last_sign_in_at")
    private long lastSignInAt;
    private boolean banned;
    private boolean locked;
    @JsonProperty("lockout_expires_in_seconds")
    private long lockoutExpiresInSeconds;

    @JsonProperty("verification_attempts_remaining")
    private long verificationAttemptsRemaining;

    @JsonProperty("updated_at")
    private long updatedAt;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("delete_self_enabled")
    private boolean deleteSelfEnabled;

    @JsonProperty("create_organization_enabled")
    private boolean createOrganizationEnabled;

    @JsonProperty("last_active_at")
    private long lastActiveAt;
}
