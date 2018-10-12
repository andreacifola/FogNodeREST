package fognoderest.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.entities.FogNode;
import fognoderest.utils.GetMiddlewareStatus;
import fognoderest.utils.SendPostRequest;

import java.io.IOException;

public class RegistrationHandler {

    private ObjectMapper mapper = new ObjectMapper();

    public FogNode sendPostRequestForRegistration(String requestUrl, String payload) throws IOException, InterruptedException {
        GetMiddlewareStatus getMiddlewareStatus = new GetMiddlewareStatus();
        while (getMiddlewareStatus.getStatus("http://localhost:8080/registration") != 200) {
            Thread.sleep(1000);
        }
        SendPostRequest sendPostRequest = new SendPostRequest();
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), FogNode.class);
    }
}
