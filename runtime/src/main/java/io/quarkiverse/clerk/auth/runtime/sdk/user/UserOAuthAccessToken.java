package io.quarkiverse.clerk.auth.runtime.sdk.user;

import lombok.Data;

@Data
public class UserOAuthAccessToken {
    private String object;
    private String token;
    private String provider;
    private String publicMetadata;
    private String label;
    private String[] scopes;
    private String tokenSecret;
}
