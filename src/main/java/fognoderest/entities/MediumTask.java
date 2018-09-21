package fognoderest.entities;

public class MediumTask extends Task {

    private int encrypt;
    private long time;

    public int getNumber() {
        return encrypt;
    }

    public void setNumber(int number) {
        this.encrypt = number;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
