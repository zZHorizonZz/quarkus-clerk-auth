package io.quarkiverse.clerk.auth.runtime.sdk.user;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import io.quarkiverse.clerk.auth.runtime.sdk.BaseService;
import io.quarkiverse.clerk.auth.runtime.sdk.Client;
import io.quarkiverse.clerk.auth.runtime.sdk.user.params.CreateUserParams;
import io.quarkiverse.clerk.auth.runtime.sdk.user.params.ListAllUsersParams;
import io.quarkiverse.clerk.auth.runtime.sdk.user.params.ListMembershipsParams;
import io.quarkiverse.clerk.auth.runtime.sdk.user.request.UpdateUserMetadataRequest;
import io.quarkiverse.clerk.auth.runtime.sdk.user.request.UpdateUserRequest;
import io.quarkiverse.clerk.auth.runtime.sdk.user.response.DeleteResponse;
import io.quarkiverse.clerk.auth.runtime.sdk.user.response.ListOrganizationMembershipsResponse;
import io.quarkiverse.clerk.auth.runtime.sdk.user.response.UserDisableMFAResponse;

public class UserService extends BaseService {

    private static final String USERS_URL = "users";

    public UserService(Client client) {
        super(client);
    }

    public User createUser(CreateUserParams params) throws Exception {
        HttpRequest request = newRequest("POST", USERS_URL, params);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Error handling based on status code
        if (response.statusCode() != 200) {
            throw new Exception("Failed to create user: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), User.class);
    }

    public List<User> listAll(ListAllUsersParams params) throws Exception {
        String url = USERS_URL + "?limit=" + params.getLimit() + "&offset=" + params.getOffset();
        if (params.getOrderBy() != null) {
            url += "&order_by=" + params.getOrderBy();
        }

        HttpRequest request = newRequest("GET", url, null);
        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Error handling based on status code
        if (response.statusCode() != 200) {
            throw new Exception("Failed to list users: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), new TypeReference<>() {
        });
    }

    public UserCount count(ListAllUsersParams params) throws Exception {
        String url = addUserSearchParamsToRequest(USERS_URL + "/count", params);
        HttpRequest request = newRequest("GET", url, null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Error handling
        if (response.statusCode() != 200) {
            throw new Exception("Failed to get user count: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), UserCount.class);
    }

    private String addUserSearchParamsToRequest(String url, ListAllUsersParams params) {
        StringBuilder query = new StringBuilder();

        if (params.getEmailAddresses() != null) {
            for (String email : params.getEmailAddresses()) {
                query.append("&email_address=").append(email);
            }
        }

        if (params.getPhoneNumbers() != null) {
            for (String phone : params.getPhoneNumbers()) {
                query.append("&phone_number=").append(phone);
            }
        }

        if (params.getExternalIDs() != null) {
            for (String externalID : params.getExternalIDs()) {
                query.append("&external_id=").append(externalID);
            }
        }

        if (params.getWeb3Wallets() != null) {
            for (String wallet : params.getWeb3Wallets()) {
                query.append("&web3_wallet=").append(wallet);
            }
        }

        if (params.getUsernames() != null) {
            for (String username : params.getUsernames()) {
                query.append("&username=").append(username);
            }
        }

        if (params.getUserIDs() != null) {
            for (String userID : params.getUserIDs()) {
                query.append("&user_id=").append(userID);
            }
        }

        if (params.getQuery() != null) {
            query.append("&query=").append(params.getQuery());
        }

        if (params.getLastActiveAtSince() != 0) {
            query.append("&last_active_at_since=").append(params.getLastActiveAtSince());
        }

        return url + "?" + query.substring(1);
    }

    public User read(String userId) throws Exception {
        String userUrl = USERS_URL + "/" + userId;
        HttpRequest request = newRequest("GET", userUrl, null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to read user: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), User.class);
    }

    public DeleteResponse delete(String userId) throws Exception {
        String userUrl = USERS_URL + "/" + userId;
        HttpRequest request = newRequest("DELETE", userUrl, null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to delete user: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), DeleteResponse.class);
    }

    public User update(String userId, UpdateUserRequest updateRequest) throws Exception {
        String userUrl = USERS_URL + "/" + userId;
        HttpRequest request = newRequest("PATCH", userUrl, updateRequest);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to update user: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), User.class);
    }

    public User updateMetadata(String userId, UpdateUserMetadataRequest updateMetadataRequest) throws Exception {
        String updateUserMetadataURL = USERS_URL + "/" + userId + "/metadata";
        HttpRequest request = newRequest("PATCH", updateUserMetadataURL, updateMetadataRequest);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to update user metadata: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), User.class);
    }

    public List<UserOAuthAccessToken> listOAuthAccessTokens(String userID, String provider) throws Exception {
        String url = USERS_URL + "/" + userID + "/oauth_access_tokens/" + provider;
        HttpRequest request = newRequest("GET", url, null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to list OAuth access tokens: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), new TypeReference<>() {
        });
    }

    public UserDisableMFAResponse disableMFA(String userId) throws Exception {
        String url = USERS_URL + "/" + userId + "/mfa";
        HttpRequest request = newRequest("DELETE", url, null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to disable MFA: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), UserDisableMFAResponse.class);
    }

    public User ban(String userId) throws Exception {
        return performUserAction(userId, "ban");
    }

    public User unban(String userId) throws Exception {
        return performUserAction(userId, "unban");
    }

    public User lock(String userId) throws Exception {
        return performUserAction(userId, "lock");
    }

    public User unlock(String userId) throws Exception {
        return performUserAction(userId, "unlock");
    }

    private User performUserAction(String userId, String action) throws Exception {
        String url = USERS_URL + "/" + userId + "/" + action;
        HttpRequest request = newRequest("POST", url, null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to " + action + " user: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), User.class);
    }

    public ListOrganizationMembershipsResponse listMemberships(ListMembershipsParams params) throws Exception {
        String url = USERS_URL + "/" + params.getUserID() + "/organization_memberships";
        URI uri = buildUriWithPagination(url, params.getLimit(), params.getOffset());

        HttpRequest request = newRequest("GET", uri.toString(), null);

        HttpResponse<String> response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Failed to list memberships: HTTP error code " + response.statusCode());
        }

        return getObjectMapper().readValue(response.body(), ListOrganizationMembershipsResponse.class);
    }

    private URI buildUriWithPagination(String baseUrl, int limit, int offset) {
        String query = String.format("limit=%d&offset=%d", limit, offset);
        return URI.create(baseUrl + "?" + URLEncoder.encode(query, StandardCharsets.UTF_8));
    }
}
