package io.quarkiverse.clerk.auth.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ClerkAuthResourceTest {

    private static final String token = "eyJhbGciOiJSUzI1NiIsImNhdCI6ImNsX0I3ZDRQRDExMUFBQSIsImtpZCI6Imluc18yYW9COFgzUmpXd1o5OUFTWFNueVpPdVFkd1UiLCJ0eXAiOiJKV1QifQ.eyJhenAiOiJodHRwOi8vbG9jYWxob3N0OjMwMDAiLCJleHAiOjE3MDU0MDQ3NjgsImlhdCI6MTcwNTQwNDcwOCwiaXNzIjoiaHR0cHM6Ly9hbGxvd2VkLWdyb3VwZXItNi5jbGVyay5hY2NvdW50cy5kZXYiLCJuYmYiOjE3MDU0MDQ2OTgsInNpZCI6InNlc3NfMmFxR0gwRGVObjhXeTRhd0hvd3QxbUhEVGd5Iiwic3ViIjoidXNlcl8yYW9zUEpxenJoMjNxVXdZS1dLOURvcUZicTUifQ.vCJjUoJ-CpZQ6BMrnHGwuPazMC9G43gja9_7PLwnek_2ydRbZWjHAEgsTE7ZG4COsSeV1iJY525K-pwshQJ4IenEb2_d2fBUeyWYLKE7jp30O3ujhkIAm7SlFym24V2U_rfkyHUGTZW9-1hNQBjZwbeSuslaVafAPhLzkjtO9gzqCC4emDcQ78E1H9a8mqfMWdOw9Bhn9RK_LjW392L_8boHhmrZg37G0i8tV558NAnApu3EEeL7uZnw4qhruGvj3NZnXDMxqt57U3x9ue22SHnIJ0QdcVHgyCeV4lS0T3vpA6DLJRu-wekwnELkxKQxShfO0cUfzpc3CBS7oT4FYA";

    public void testHelloEndpoint() {
        given()
                .when()
                .header(HttpHeaderNames.AUTHORIZATION.toString(), "Bearer " + token)
                .get("/clerk-auth")
                .then()
                .statusCode(200)
                .body(is("Hello clerk-auth"));
    }
}
