package fognoderest.entities;

public class MediumTask extends Task {

    private int encrypt;
    private int time;

    public int getNumber() {
        return encrypt;
    }

    public void setNumber(int number) {
        this.encrypt = number;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
