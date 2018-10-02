package fognoderest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.entities.MediumTaskState;
import fognoderest.utils.SendPostRequest;

import java.io.IOException;


public class GetStateHandler {

    private ObjectMapper mapper = new ObjectMapper();

    public void sendMediumTaskState(Integer state, Long currentTime, Integer taskId) throws IOException {

        String payload = mediumTaskStateToJson(taskId, state, currentTime);
        String requestUrl = "http://localhost:8080/mediumTaskState";
        sendPostRequestForTaskState(requestUrl, payload);

    }

    private String mediumTaskStateToJson(Integer taskId, Integer state, Long currentTime) {
        String payload = "{ \"mediumTaskId\" : " + taskId + ", \"state\" : " + state +
                ", \"currentTime\" : " + currentTime + "}";
        return payload;
    }

    private MediumTaskState sendPostRequestForTaskState(String requestUrl, String payload) throws IOException {
        SendPostRequest sendPostRequest = new SendPostRequest();
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), MediumTaskState.class);
    }
}
