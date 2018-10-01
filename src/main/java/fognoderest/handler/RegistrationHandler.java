package fognoderest.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.entities.FogNode;
import fognoderest.utils.SendPostRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistrationHandler {

    private ObjectMapper mapper = new ObjectMapper();

    public FogNode sendPostRequestForRegistration(String requestUrl, String payload) throws IOException {
        SendPostRequest sendPostRequest = new SendPostRequest();
        StringBuilder jsonString = sendPostRequest.sendPostRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), FogNode.class);
    }
}
