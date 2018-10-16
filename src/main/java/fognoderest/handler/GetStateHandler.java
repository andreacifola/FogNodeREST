package fognoderest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.entities.HeavyTask;
import fognoderest.entities.HeavyTaskState;
import fognoderest.entities.LightTaskState;
import fognoderest.entities.MediumTaskState;
import fognoderest.utils.SendPostRequest;
import java.io.IOException;
import java.math.BigInteger;

public class GetStateHandler {

    private SendPostRequest sendPostRequest = new SendPostRequest();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * This method sends the state of a light task to the middleware
     *
     * @param loopCount is the number of the loop in the solver til that moment
     * @param encrypted is the encrypted string til that moment
     * @param taskId    is the id of the task of which we send the task
     * @throws IOException because of the POST
     */
    public void sendLightTaskState(Integer loopCount, String encrypted, Integer taskId) throws IOException {
        String payload = lightTaskStateToJson(loopCount, encrypted, taskId);
        String requestUrl = "http://localhost:8080/state/light";
        sendPostRequestForLightTaskState(requestUrl, payload);
    }

    /**
     * This method creates the string that represents the JSON object of a light task state
     * @param loopCount is the number of the loop in the solver til that moment
     * @param encrypted is the encrypted string til that moment
     * @param taskId is the id of the task of which we send the task
     * @return the string that represents the JSON object of a light task state
     */
    private String lightTaskStateToJson(Integer loopCount, String encrypted, Integer taskId) {
        String payload = "{ \"lightTaskId\" : " + taskId + ", \"loopCount\" : " + loopCount +
                ", \"encrypted\" : \"" + encrypted + "\"}";
        return payload;
    }

    private LightTaskState sendPostRequestForLightTaskState(String requestUrl, String payload) throws IOException {
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), LightTaskState.class);
    }

    /**
     * This method sends the state of a medium task to the middleware
     * @param state is the number of the loop in the solver til that moment
     * @param currentTime is the time of the execution til that moment
     * @param taskId is the id of the task of which we send the task
     * @throws IOException because of the POST
     */
    public void sendMediumTaskState(Integer state, Long currentTime, Integer taskId) throws IOException {
        String payload = mediumTaskStateToJson(taskId, state, currentTime);
        String requestUrl = "http://localhost:8080/state/medium";
        sendPostRequestForMediumTaskState(requestUrl, payload);

    }

    /**
     * This method creates the string that represents the JSON object of a light task state
     * @param state is the number of the loop in the solver til that moment
     * @param currentTime is the time of the execution til that moment
     * @param taskId is the id of the task of which we send the task
     * @return the string that represents the JSON object of a light task state
     */
    private String mediumTaskStateToJson(Integer taskId, Integer state, Long currentTime) {
        String payload = "{ \"mediumTaskId\" : " + taskId + ", \"state\" : " + state +
                ", \"currentTime\" : " + currentTime + "}";
        return payload;
    }

    private MediumTaskState sendPostRequestForMediumTaskState(String requestUrl, String payload) throws IOException {
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), MediumTaskState.class);
    }

    /**
     * This method sends the state of a heavy task to the middleware
     *
     * @param taskId
     * @param partial
     * @param last
     * @throws IOException because of the POST
     */
    public void sendHeavyTaskState(Integer taskId, BigInteger partial, int last) throws IOException {
        String payload = heavyTaskStateToJson(taskId, partial, last);
        String requestUrl = "http://localhost:8080/state/heavy";
        sendPostRequestForHeavyTaskState(requestUrl, payload);
    }

    /**
     * This method creates the string that represents the JSON object of a heavy task state
     * @param taskId
     * @param partial
     * @param last
     * @return the string that represents the JSON object of a heavy task state
     */
    private String heavyTaskStateToJson(Integer taskId, BigInteger partial, int last) {
        String payload = "{ \"heavyTaskId\" : " + taskId + ", \"partial\" : " + partial +
                ", \"last\" : \"" + last + "\"}";
        return payload;
    }

    private HeavyTaskState sendPostRequestForHeavyTaskState(String requestUrl, String payload) throws IOException {
        StringBuilder jsonString = sendPostRequest.postRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), HeavyTaskState.class);
    }
}
