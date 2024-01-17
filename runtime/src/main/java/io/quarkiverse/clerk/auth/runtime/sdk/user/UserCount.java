package io.quarkiverse.clerk.auth.runtime.sdk.user;

import lombok.Data;

@Data
public class UserCount {
    private String object;
    private int totalCount;
}
