package fognoderest.entities;

public class HeavyTask extends Task {

    private Long n = 45L;
    private Long response;

    public Long getResponse() {
        return response;
    }
    public void setResponse(Long response) {
        this.response = response;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }
}
