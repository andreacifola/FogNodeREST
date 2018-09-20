package fognoderest.utils;


import fognoderest.entities.Node;

public class JsonBuilder {

    public String nodeToJson(Node node){
        String payload = "{ \"id\" :" + node.getId() + ", \"ram\" : " + node.getRam() + ", \"cpu\" : " + node.getCpu() +
                ", \"totalBattery\" : " + node.getBattery() + ", \"storage\" : " + node.getStorage() + "}";
        return payload;
    }
}
