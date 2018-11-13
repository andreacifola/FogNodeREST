package com.example.FogNodeREST3.handler;


import com.example.FogNodeREST3.utils.GetMiddlewareStatus;
import com.example.FogNodeREST3.utils.SendPostRequest;

import java.io.IOException;

public class RegistrationHandler {

    //private ObjectMapper mapper = new ObjectMapper();
    private static String mwIp = "a6ce72bb8e6e211e886ee02a7517efe7-507914080.us-east-2.elb.amazonaws.com";

    public void sendPostRequestForRegistration(String requestUrl, String payload) throws IOException, InterruptedException {
        GetMiddlewareStatus getMiddlewareStatus = new GetMiddlewareStatus();
        //TODO indirizzo mw
        while (getMiddlewareStatus.getStatus("http://localhost:8080/registration") != 200) {
        //while (getMiddlewareStatus.getStatus("http://" + mwIp + ":8080/registration") != 200) {
            Thread.sleep(1000);
        }
        SendPostRequest sendPostRequest = new SendPostRequest();
        sendPostRequest.postRequest(requestUrl, payload);
    }
}