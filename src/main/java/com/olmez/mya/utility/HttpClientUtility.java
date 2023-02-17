package com.olmez.mya.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpClientUtility {

    public static InputStream getResponseAsStream(String url) throws IOException, InterruptedException {
        HttpRequest request = createRequest(url);
        return getResponseAsStreamFromRequest(request);
    }

    private HttpRequest createRequest(String url) {
        return HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .GET()
                .build();
    }

    private InputStream getResponseAsStreamFromRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        return response.body();
    }

}
