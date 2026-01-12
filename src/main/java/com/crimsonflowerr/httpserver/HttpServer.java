package com.crimsonflowerr.httpserver;

import com.crimsonflowerr.httpserver.config.Configuration;
import com.crimsonflowerr.httpserver.config.ConfigurationManager;
import com.crimsonflowerr.httpserver.config.HttpConfigurationException;
import com.crimsonflowerr.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) throws HttpConfigurationException {
        LOGGER.info("Server Starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src//main//resources//http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using Port: " + configuration.getPort());
        LOGGER.info("Using Webroot: " + configuration.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(configuration.getPort(), configuration.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
