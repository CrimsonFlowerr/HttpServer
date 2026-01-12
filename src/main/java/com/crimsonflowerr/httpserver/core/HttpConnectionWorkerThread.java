package com.crimsonflowerr.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpConnectionWorkerThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            //Purpose was to print the request given by the browser in terminal
//            int _byte;
//            while((_byte = inputStream.read()) >= 0) {
//                System.out.print((char) _byte);
//            }



            //TODO Read
            String request = "<html><head><title>Sample HTTP page</title></head><body><h1>Sample HTTP Page</h1></body></html>";

            //TODO Write
            final String CRLF = "\r\n"; //13, 10 ASCII
            String response =
                    "HTTP/1.1 200 OK" + CRLF +// Status Line : "HTTP/VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + (request.getBytes(StandardCharsets.UTF_8)).length + CRLF + //HEADER
                            CRLF +
                            request +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());


            LOGGER.info("Connection Processing Finished...");
        } catch (IOException e) {
            LOGGER.error("Problem Processing Thread: " + e);
            e.printStackTrace();
        }
        finally {
            try {
                if(inputStream != null) inputStream.close();
            } catch (IOException e) {}
            try {
                if(outputStream != null) outputStream.close();
            } catch (IOException e) {}
            try {
                if(socket != null) socket.close();
            } catch (IOException e) {}
        }
    }
}
