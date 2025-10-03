package org.gycoding.books.infrastructure.external.gyaccounts;

import kong.unirest.HttpResponse;
import org.gycoding.books.domain.repository.GYAccountsFacade;
import org.gycoding.books.infrastructure.external.unirest.UnirestFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class GYAccountsFacadeImpl implements GYAccountsFacade {
    private @Value("${gy.accounts.url}") String url;
    private @Value("${allowed.apiKey}") String apiKey;

    @Override
    public String getUserId(String profileId) {
        final var headers = new HashMap<String, String>();

        headers.put("Content-Type", "application/json");

        HttpResponse<String> response = UnirestFacade.post(
                url + "/user/transform/userId",
                headers,
                String.format("{\"profileId\": \"%s\"}", profileId)
        );

        return response.getBody();
    }

    @Override
    public UUID getProfileId(String userId) {
        final var headers = new HashMap<String, String>();

        headers.put("Content-Type", "application/json");

        HttpResponse<String> response = UnirestFacade.post(
                url + "/user/transform/profileId",
                headers,
                String.format("{\"userId\": \"%s\"}", userId)
        );

        return UUID.fromString(response.getBody().replace("\"", ""));
    }
}
