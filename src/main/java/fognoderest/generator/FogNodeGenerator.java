package fognoderest.generator;


import fognoderest.entities.FogNode;
import fognoderest.utils.RandomNumberGenerator;

public class FogNodeGenerator {

    public FogNode spawnFogNode(Integer id, Integer randomNumber) {
        FogNode fogNode = new FogNode();
        //Integer randomNumber = new RandomNumberGenerator().generateRandom(1,3);
        if (randomNumber == 1)
            generateFogNodeParameters(fogNode, id, "LIGHT", 11, 39);
        else if (randomNumber == 2)
            generateFogNodeParameters(fogNode, id, "MEDIUM", 40, 79);
        else
            generateFogNodeParameters(fogNode, id, "HEAVY", 80, 100);
        return fogNode;
    }

    private void generateFogNodeParameters(FogNode fogNode, Integer id, String type, Integer start, Integer end) {
        fogNode.setId(id);
        fogNode.setType(type);
        fogNode.setRam(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setCpu(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setBattery(new RandomNumberGenerator().generateRandom(start*1000*100, end*1000*100));
        fogNode.setStorage(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setCurrentRam(fogNode.getRam());
        fogNode.setCurrentCpu(fogNode.getCpu());
        fogNode.setCurrentBattery(fogNode.getBattery().floatValue());
        fogNode.setCurrentStorage(fogNode.getStorage());
    }
}
