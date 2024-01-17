/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.quarkiverse.clerk.auth.it;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import io.quarkiverse.clerk.auth.runtime.sdk.Client;
import io.quarkus.security.Authenticated;
import io.quarkus.security.runtime.SecurityIdentityAssociation;

@Path("/clerk-auth")
@ApplicationScoped
public class ClerkAuthResource {
    // add some rest methods here

    @Inject
    SecurityIdentityAssociation securityIdentityAssociation;

    @Inject
    Client client;

    @GET
    @Authenticated
    public Response hello() {
        try {
            return Response.ok(client.getUserService()
                    .read(securityIdentityAssociation.getIdentity().getPrincipal().getName())).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/admin")
    public Response admin() {
        try {
            return Response.ok(client.getUserService()
                    .read("user_2awQf4jw3VzgQIjswAijnJ4R8Qs")).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
