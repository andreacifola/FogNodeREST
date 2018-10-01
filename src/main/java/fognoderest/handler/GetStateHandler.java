package fognoderest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fognoderest.utils.SendPostRequest;
import java.io.IOException;


public class GetStateHandler {

    private ObjectMapper mapper = new ObjectMapper();

    public void sendMediumTaskState(Integer state, Long currentTime, Integer taskId) throws IOException {

        String payload = mediumTaskStateToJson(taskId, state, currentTime);
        String requestUrl="http://localhost:8080/state/mediumTask";
        sendPostRequestForTaskState(requestUrl, payload);

    }

    private String mediumTaskStateToJson(Integer taskId, Integer state, Long currentTime) {
        String payload = "{ \"mediumTaskId\" : " + taskId + ", \"state\" : " + state +
                "\"currentTime\" : " + currentTime + "}";
        return payload;
    }

    private MediumTaskState sendPostRequestForTaskState(String requestUrl, String payload) throws IOException {
        SendPostRequest sendPostRequest = new SendPostRequest();
        StringBuilder jsonString = sendPostRequest.sendPostRequest(requestUrl, payload);
        return mapper.readValue(jsonString.toString(), MediumTaskState.class);
    }


    private class MediumTaskState {
        private Integer taskId;
        private Integer state;
        private Integer currentTime;

        public MediumTaskState(Integer taskId, Integer state, Integer currentTime) {
            this.taskId = taskId;
            this.state = state;
            this.currentTime = currentTime;
        }

        public Integer getTaskId() {
            return taskId;
        }

        public void setTaskId(Integer taskId) {
            this.taskId = taskId;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(Integer currentTime) {
            this.currentTime = currentTime;
        }
    }
}
