package fognoderest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.entities.FogNode;
import fognoderest.utils.SendPostRequest;
import java.io.IOException;

public class RegistrationHandler {

    private ObjectMapper mapper = new ObjectMapper();

    public FogNode sendPostRequestForRegistration(String requestUrl, String payload) throws IOException {
    //public void sendPostRequestForRegistration(String requestUrl, String payload) throws IOException {
        SendPostRequest sendPostRequest = new SendPostRequest();
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), FogNode.class);
    }
}