package fognoderest.utils;


import fognoderest.entities.FogNode;

public class JsonBuilder {

    public String nodeToJson(FogNode fogNode){
        String payload = "{ \"id\" : " + fogNode.getId() + ", \"ram\" : " + fogNode.getRam() + ", \"cpu\" : " +
                fogNode.getCpu() + ", \"battery\" : " + fogNode.getBattery() + ", \"storage\" : " +
                fogNode.getStorage() + ", \"type\" : " + "\"" + fogNode.getType() + "\"" + ", \"port\" : " +
                "\"" + fogNode.getPort() + "\", \"currentRam\" : " + fogNode.getCurrentRam() + ", \"currentCpu\" : " +
                fogNode.getCurrentCpu() + ", \"currentBattery\" : " + fogNode.getCurrentBattery() +
                ", \"currentStorage\" : " + fogNode.getCurrentStorage() + ", \"latitude\" : " +
                fogNode.getLatitude() + ", \"longitude\" : " + fogNode.getLongitude() +
                ", \"powered\" : \"" + fogNode.getPowered() + "\"}";
        return payload;
    }
}
