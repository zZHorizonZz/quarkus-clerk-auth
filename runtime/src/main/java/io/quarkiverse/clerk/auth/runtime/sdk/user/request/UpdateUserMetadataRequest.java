package io.quarkiverse.clerk.auth.runtime.sdk.user.request;

import lombok.Data;

@Data
public class UpdateUserMetadataRequest {
    private String publicMetadata;
    private String privateMetadata;
    private String unsafeMetadata;
}
