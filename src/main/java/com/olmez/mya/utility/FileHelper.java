package com.olmez.mya.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileHelper {

	private final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * This allows reading the file given its url according to the class type specified.
	 * @param <T> class type
	 * @param sourceUrl the url of the file to be read (e.g. "/currency/rates.json")
	 * @param objType
	 * @return An object of the given object type
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static <T> T readFile(boolean testMode, String sourceUrl, Class<T> objType) throws IOException, InterruptedException {
		if(testMode){
			InputStream is = FileHelper.class.getResourceAsStream(sourceUrl);
			return objectMapper.readValue(is, objType);
		}
		return readFile(sourceUrl, objType);
	}

	private <T> T readFile(String sourceUrl, Class<T> objType) throws IOException, InterruptedException {
		InputStream is = getResponseAsStream(sourceUrl);
		return objectMapper.readValue(is, objType);
	}

	private InputStream getResponseAsStream(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        return response.body();
    }

	
}
