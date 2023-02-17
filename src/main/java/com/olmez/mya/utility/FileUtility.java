package com.olmez.mya.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class FileUtility {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final int BUFFER_SIZE = 1024;

	// READ FILE BASED ON JAVA CLASSES *********************************************
	/**
	 * This allows to read the test file on the project according to the class type
	 * specified.
	 * 
	 * @param <T>     class type
	 * @param source  the url of the file on the project resources (e.g.
	 *                "/currency/rates.json")
	 * @param objType
	 * @return An object of the given object type
	 * @throws IOException
	 */
	public static <T> T readFileOnTestMode(String source, Class<T> objType) throws IOException {
		InputStream is = FileUtility.class.getResourceAsStream(source);
		return MAPPER.readValue(is, objType);
	}

	/**
	 * This allows to read the file on the web according to the class type
	 * specified.
	 * 
	 * @param <T>       class type
	 * @param sourceUrl the url on the web (e.g. "api/v1/currency/rates.json")
	 * @param objType
	 * @return An object of the given object type
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static <T> T readFile(String sourceUrl, Class<T> objType) throws IOException, InterruptedException {
		InputStream is = HttpClientUtility.getResponseAsStream(sourceUrl);
		return MAPPER.readValue(is, objType);
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
