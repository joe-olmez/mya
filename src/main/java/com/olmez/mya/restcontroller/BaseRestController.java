package com.olmez.mya.restcontroller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseRestController {

    protected void returnError(HttpServletResponse response, String message) throws IOException {
        returnError(response, message, null);
    }

    protected void returnError(HttpServletResponse response, String message, Exception e) throws IOException {
        if (e != null) {
            log.error(message, e);
        } else {
            log.error(message);
        }
        printMessage(response, message);
    }

    protected void returnWarn(HttpServletResponse response, String message) throws IOException {
        log.warn(message);
        printMessage(response, message);
    }

    protected void printMessage(HttpServletResponse response, String message) throws IOException {
        response.getOutputStream().print(message);
    }

    /**
     * This method compares the size of the file and the number of bytes streamed to
     * the client, and logs accordingly.
     * 
     * @param file
     * @param numBytesStreamed
     * @return true if size of file and number of bytes streamed to the client are
     *         equal; otherwise, return false
     */
    protected boolean verifyDownloadedFileSize(File file, long numBytesStreamed) {
        if (numBytesStreamed != file.length()) {
            log.error("Download Failed: {} File Size: {} bytes - Streamed: {} bytes", file.getName(), file.length(),
                    numBytesStreamed);
            return false;
        }
        log.info("Download Successful: {} File Size: {} bytes - Streamed: {} bytes", file.getName(), file.length(),
                numBytesStreamed);
        return true;
    }
}
