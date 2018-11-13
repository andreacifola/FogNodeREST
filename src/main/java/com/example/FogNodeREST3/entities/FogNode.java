package com.example.FogNodeREST3.entities;

public class FogNode {
    private Integer id;
    private Integer ram;
    private Integer cpu;
    private Integer battery;
    private Integer storage;
    private String type;
    private String port;
    private Integer currentRam;
    private Integer currentCpu;
    private Float currentBattery;
    private Integer currentStorage;
    private Double latitude;
    private Double longitude;
    private String powered;

    public FogNode() { }

    public FogNode(Integer id, Integer ram, Integer cpu, Integer battery, Integer storage, String type,
                   String port, Integer currentRam, Integer currentCpu, Float currentBattery,
                   Integer currentStorage, Double latitude, Double longitude, String powered) {
        this.id = id;
        this.ram = ram;
        this.cpu = cpu;
        this.battery = battery;
        this.storage = storage;
        this.type = type;
        this.port = port;
        this.currentRam = currentRam;
        this.currentCpu = currentCpu;
        this.currentBattery = currentBattery;
        this.currentStorage = currentStorage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.powered = powered;
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getCurrentRam() {
        return currentRam;
    }

    public void setCurrentRam(Integer currentRam) {
        this.currentRam = currentRam;
    }

    public Integer getCurrentCpu() {
        return currentCpu;
    }

    public void setCurrentCpu(Integer currentCpu) {
        this.currentCpu = currentCpu;
    }

    public Float getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(Float currentBattery) {
        this.currentBattery = currentBattery;
    }

    public Integer getCurrentStorage() {
        return currentStorage;
    }

    public void setCurrentStorage(Integer currentStorage) {
        this.currentStorage = currentStorage;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPowered() {
        return powered;
    }

    public void setPowered(String powered) {
        this.powered = powered;
    }
}
