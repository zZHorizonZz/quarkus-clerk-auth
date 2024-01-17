package io.quarkiverse.clerk.auth.runtime.sdk.user.response;

import lombok.Data;

@Data
public class DeleteResponse {
    private String id;
    private String slug;
    private String object;
    private boolean deleted;
}
