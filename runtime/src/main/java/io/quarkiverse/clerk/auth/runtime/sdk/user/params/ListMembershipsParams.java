package io.quarkiverse.clerk.auth.runtime.sdk.user.params;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListMembershipsParams {
    private int limit;
    private int offset;
    private String userID;
}
