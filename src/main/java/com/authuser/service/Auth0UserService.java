package com.authuser.service;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.RolesFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.roles.Role;
import com.auth0.json.mgmt.roles.RolesPage;
import com.auth0.json.mgmt.users.User;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Auth0UserService {

    private static final Logger logger = LoggerFactory.getLogger(Auth0UserService.class);

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.mgmt-client-id}")
    private String clientId;

    @Value("${auth0.mgmt-client-secret}")
    private String clientSecret;

    @Value("${auth0.audience}")
    private String audience;

    private ManagementAPI managementAPI;

    @PostConstruct
    public void init() throws Auth0Exception {
        logger.info("Initializing Auth0UserService and fetching Management API token...");
        try {
            String token = Auth0TokenUtil.fetchManagementApiToken(domain, clientId, clientSecret, audience);
            this.managementAPI = new ManagementAPI(domain, token);
            logger.info("Auth0 ManagementAPI client initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize Auth0UserService", e);
            throw e;
        }
    }

    public void registerUserInAuth0(String email, String password, String username,String roleName) throws Auth0Exception {
        logger.info("Registering user with email: {} and username: {}", email, username);

        User user = new User("Username-Password-Authentication");
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setConnection("Username-Password-Authentication");
        user.setEmailVerified(false);
        user.setVerifyEmail(false);

        User createdUser = managementAPI.users().create(user).execute().getBody();
        logger.info("Created Auth0 user with ID: {}", createdUser.getId());

        RolesFilter rolesFilter = new RolesFilter().withPage(0, 25);
        RolesPage rolesPage = managementAPI.roles().list(rolesFilter).execute().getBody();

        Role role = rolesPage.getItems()
                .stream()
                .filter(r -> r.getName().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        logger.info("Assigning role '{}' to user ID {}", roleName, createdUser.getId());
        managementAPI.users().addRoles(createdUser.getId(), Collections.singletonList(role.getId())).execute();
    }
}
