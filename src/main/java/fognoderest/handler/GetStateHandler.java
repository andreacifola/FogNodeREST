package fognoderest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.entities.LightTaskState;
import fognoderest.entities.MediumTaskState;
import fognoderest.utils.SendPostRequest;

import java.io.IOException;


public class GetStateHandler {

    private SendPostRequest sendPostRequest = new SendPostRequest();
    private ObjectMapper mapper = new ObjectMapper();

    public void sendLightTaskState(Integer loopCount, String encrypted, Integer taskId) throws IOException {
        String payload = lightTaskStateToJson(loopCount, encrypted, taskId);
        String requestUrl = "http://localhost:8080/state/light";
        sendPostRequestForLightTaskState(requestUrl, payload);
    }

    private String lightTaskStateToJson(Integer loopCount, String encrypted, Integer taskId) {
        String payload = "{ \"lightTaskId\" : " + taskId + ", \"loopCount\" : " + loopCount +
                ", \"encrypted\" : \"" + encrypted + "\"}";
        return payload;
    }

    private LightTaskState sendPostRequestForLightTaskState(String requestUrl, String payload) throws IOException {
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), LightTaskState.class);
    }

    public void sendMediumTaskState(Integer state, Long currentTime, Integer taskId) throws IOException {
        String payload = mediumTaskStateToJson(taskId, state, currentTime);
        String requestUrl = "http://localhost:8080/state/medium";
        sendPostRequestForMediumTaskState(requestUrl, payload);

    }

    private String mediumTaskStateToJson(Integer taskId, Integer state, Long currentTime) {
        String payload = "{ \"mediumTaskId\" : " + taskId + ", \"state\" : " + state +
                ", \"currentTime\" : " + currentTime + "}";
        return payload;
    }

    private MediumTaskState sendPostRequestForMediumTaskState(String requestUrl, String payload) throws IOException {
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), MediumTaskState.class);
    }
}
