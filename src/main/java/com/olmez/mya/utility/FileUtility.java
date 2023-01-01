package com.olmez.mya.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class FileUtility {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final int BUFFER_SIZE = 1024;

	// READ FILE BASED ON JAVA CLASSESS*********************************************
	/**
	 * This allows reading the file given its url according to the class type
	 * specified.
	 * 
	 * @param <T>       class type
	 * @param sourceUrl the url of the file to be read (e.g. "/currency/rates.json")
	 * @param objType
	 * @return An object of the given object type
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static <T> T readFile(boolean testMode, String sourceUrl, Class<T> objType)
			throws IOException, InterruptedException {
		if (testMode) {
			InputStream is = FileUtility.class.getResourceAsStream(sourceUrl);
			return MAPPER.readValue(is, objType);
		}
		return readFile(sourceUrl, objType);
	}

	private <T> T readFile(String sourceUrl, Class<T> objType) throws IOException, InterruptedException {
		InputStream is = getResponseAsStream(sourceUrl);
		return MAPPER.readValue(is, objType);
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

	// GENERATE FILE *************************************************************
	/**
	 * It allows the creation of a new file by reading the given file as byte lists.
	 * This method is especially recommended for reading and writing for large
	 * files.
	 * 
	 * @param inputFileName  given file name with full path (e.g.
	 *                       C:\\temp\\existingToronto.pdf)
	 * @param outputFileName name of the file to be generated with full path (e.g.
	 *                       C:\\temp\\newToronto.pdf)
	 * @return {@code true} if it is generated one-to-one, {@code false} otherwise.
	 * @throws IOException
	 */
	public static boolean generateFile(String inputFileName, String outputFileName) throws IOException {
		if (StringUtility.isEmpty(inputFileName) || StringUtility.isEmpty(outputFileName)) {
			return false;
		}
		return generateFile(new File(inputFileName), new File(outputFileName));
	}

	/**
	 * It allows the creation of a new file by reading the given file as byte lists.
	 * This method is especially recommended for reading and writing for large
	 * files.
	 * 
	 * @param inputFile  given file
	 * @param outputFile the file to be generated
	 * @return {@code true} if it is generated one-to-one, {@code false} otherwise.
	 * @throws IOException
	 */
	public static boolean generateFile(File inputFile, File outputFile) throws IOException {
		if (inputFile == null || outputFile == null || !inputFile.exists()) {
			return false;
		}

		try (var bis = new BufferedInputStream(new FileInputStream(inputFile));
				var bos = new BufferedOutputStream(new FileOutputStream(outputFile));) {

			byte[] bytes = new byte[BUFFER_SIZE];
			int read = 0;
			long totalRead = 0L; // to verify
			while ((read = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, read);
				totalRead += read;
			}
			return verify(inputFile.length(), totalRead);
		}
	}

	private boolean verify(long inputFileLength, long totalRead) {
		var msg = String.format("File Length - Total Read Bytes: %d - %d", inputFileLength, totalRead);
		if (inputFileLength == totalRead) {
			log.info("Successful. {}", msg);
			return true;
		}
		log.info("Not Verified!! {}", msg);
		return false;
	}
	// *******************************************************************************************************

}
