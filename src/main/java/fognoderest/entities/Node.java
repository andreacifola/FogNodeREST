package fognoderest.entities;


public class Node {
    private Integer id;
    private Integer ram;
    private Integer cpu;
    private Integer battery;
    private Integer storage;
    private String type;

    public Node() { }

    public Node(Integer id, Integer ram, Integer cpu, Integer battery, Integer storage) {
        this.id = id;
        this.ram = ram;
        this.cpu = cpu;
        this.battery = battery;
        this.storage = storage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
