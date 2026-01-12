package com.crimsonflowerr.httpserver.config;

import com.crimsonflowerr.httpserver.utils.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    public ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if(myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    // used to load a configuration file in given param
    public void loadConfigurationFile(String filepath) throws HttpConfigurationException{
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;

        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        }
        catch(IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode configuration = null;
        try {
            configuration = Json.parse(sb.toString());
        } catch(JsonProcessingException e) {
            throw new RuntimeException("Error Parsing Configuration File", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(configuration, Configuration.class);
        } catch(JsonProcessingException e) {
            throw new HttpConfigurationException("Error Parsing Configuration File, Problem Outputting JSON", e);
        }
    }

    // returns currently loaded configuration
    public Configuration getCurrentConfiguration() throws HttpConfigurationException {
        if(myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration");
        }
        return myCurrentConfiguration;
    }
}
