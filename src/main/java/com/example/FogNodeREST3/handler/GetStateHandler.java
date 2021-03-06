package com.example.FogNodeREST3.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.FogNodeREST3.entities.HeavyTaskState;
import com.example.FogNodeREST3.entities.LightTaskState;
import com.example.FogNodeREST3.entities.MediumTaskState;
import com.example.FogNodeREST3.utils.SendPostRequest;
import java.io.IOException;
import java.math.BigInteger;

public class GetStateHandler {

    private SendPostRequest sendPostRequest = new SendPostRequest();
    private ObjectMapper mapper = new ObjectMapper();
    private static String mwIp = "a6ce72bb8e6e211e886ee02a7517efe7-507914080.us-east-2.elb.amazonaws.com";

    /**
     * This method sends the state of a light task to the middleware
     *
     * @param loopCount is the number of the loop in the solver til that moment
     * @param encrypted is the encrypted string til that moment
     * @param taskId    is the id of the task of which we send the task
     * @throws IOException because of the POST
     */
    public void sendLightTaskState(Integer loopCount, String encrypted, Integer taskId, int id) throws IOException {
        String payload = lightTaskStateToJson(loopCount, encrypted, taskId);
        //todo indirizzo mw
        String requestUrl = "http://localhost:8080/state/light/" + id;
        //String requestUrl = "http://" + mwIp + ":8080/state/light/" + id;
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
    public void sendMediumTaskState(Integer state, Long currentTime, Integer taskId, int id) throws IOException {
        String payload = mediumTaskStateToJson(taskId, state, currentTime);
        String requestUrl = "http://localhost:8080/state/medium/"+id;
        //String requestUrl = "http://" + mwIp + ":8080/state/medium/" + id;
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
    public void sendHeavyTaskState(Integer taskId, BigInteger partial, int last, int id) throws IOException {
        String payload = heavyTaskStateToJson(taskId, partial, last);
        String requestUrl = "http://localhost:8080/state/heavy/"+id;
        //String requestUrl = "http://" + mwIp + ":8080/state/heavy/" + id;
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
