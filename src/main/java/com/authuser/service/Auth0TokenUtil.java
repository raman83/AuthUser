package com.authuser.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Auth0TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(Auth0TokenUtil.class);

    public static String fetchManagementApiToken(String domain, String clientId, String clientSecret, String audience) {
        logger.info("Fetching Auth0 Management API token for domain: {}", domain);
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

            RequestBody body = new FormBody.Builder()
                    .add("grant_type", "client_credentials")
                    .add("client_id", clientId)
                    .add("client_secret", clientSecret)
                    .add("audience", audience)
                    .build();

            Request request = new Request.Builder()
                    .url( domain + "/oauth/token")
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            logger.debug("Auth0 token response: {}", responseBody);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(responseBody);

            if (json.has("access_token")) {
                String token = json.get("access_token").asText();
                logger.info("Successfully fetched Auth0 Management API token.");
                return token;
            } else {
                logger.error("Failed to fetch token, response did not contain access_token: {}", responseBody);
                throw new RuntimeException("Failed to get Auth0 token");
            }

        } catch (Exception e) {
            logger.error("Exception occurred while fetching Auth0 token", e);
            throw new RuntimeException("Failed to get Auth0 token", e);
        }
    }
}
