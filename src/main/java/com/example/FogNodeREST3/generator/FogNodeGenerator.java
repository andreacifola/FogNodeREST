package com.example.FogNodeREST3.generator;

import com.example.FogNodeREST3.utils.RandomNumberGenerator;
import com.example.FogNodeREST3.entities.FogNode;

public class FogNodeGenerator {

    public FogNode spawnFogNode(String port, Integer randomNumber) {

        FogNode fogNode = new FogNode();
        //TODO togliere il commento alla riga sotto
        //Integer randomNumber = new RandomNumberGenerator().generateRandom(1,3);
        if (randomNumber == 1)
            generateFogNodeParameters(fogNode, port, "LIGHT", 11, 39);
        else if (randomNumber == 2)
            generateFogNodeParameters(fogNode, port, "MEDIUM", 40, 79);
        else
            generateFogNodeParameters(fogNode, port, "HEAVY", 80, 100);
        return fogNode;
    }

    private void generateFogNodeParameters(FogNode fogNode,String port, String type, Integer start, Integer end) {
        fogNode.setType(type);
        fogNode.setRam(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setCpu(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setBattery(new RandomNumberGenerator().generateRandom(start*100, end*100));
        fogNode.setStorage(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setCurrentRam(fogNode.getRam());
        fogNode.setCurrentCpu(fogNode.getCpu());
        fogNode.setCurrentBattery(fogNode.getBattery().floatValue());
        fogNode.setCurrentStorage(fogNode.getStorage());
        fogNode.setPort(port);

        //It's approximatively the middle Italy
        Double latitude = new RandomNumberGenerator().generateRandom(41.5, 42.5);
        Double longitude = new RandomNumberGenerator().generateRandom(12.6, 13.6);
        fogNode.setLatitude(latitude);
        fogNode.setLongitude(longitude);

        Integer randomCurrentSupplied = new RandomNumberGenerator().generateRandom(1,2);
        if (randomCurrentSupplied == 1)
            fogNode.setPowered("yes");
        else
            fogNode.setPowered("no");
    }
}
