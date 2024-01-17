package io.quarkiverse.clerk.auth.runtime.sdk.user.params;

import lombok.Data;

@Data
public class ListAllUsersParams {
    private int limit;
    private int offset;
    private String[] emailAddresses;
    private String[] externalIDs;
    private String[] phoneNumbers;
    private String[] web3Wallets;
    private String[] usernames;
    private String[] userIDs;
    private String query;
    private long lastActiveAtSince;
    private String orderBy;
}
