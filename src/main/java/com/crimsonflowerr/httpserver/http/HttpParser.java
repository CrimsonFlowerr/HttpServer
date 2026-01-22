package com.crimsonflowerr.httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    public final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    public HttpRequest parseHttpRequest(InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request = new HttpRequest();
        parseRequestLine(reader, request);
        parseHeaderLine(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {

    }

    private void parseHeaderLine(InputStreamReader reader, HttpRequest request) {

    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) {

    }

}
