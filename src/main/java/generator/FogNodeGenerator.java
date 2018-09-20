package generator;


import fognoderest.entities.FogNode;
import fognoderest.utils.RandomNumberGenerator;

public class FogNodeGenerator {

    public FogNode spawnFogNode(Integer id) {
        FogNode fogNode = new FogNode();
        Integer randomNumber = new RandomNumberGenerator().generateRandom(1,3);
        if (randomNumber == 1)
            generateFogNodeParameters(fogNode, id, "LIGHT", 1, 3);
        else if (randomNumber == 2)
            generateFogNodeParameters(fogNode, id, "MEDIUM", 4, 7);
        else
            generateFogNodeParameters(fogNode, id, "HEAVY", 8, 10);
        return fogNode;
    }

    private void generateFogNodeParameters(FogNode fogNode, Integer id, String type, Integer start, Integer end) {
        fogNode.setId(id);
        fogNode.setType(type);
        fogNode.setRam(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setCpu(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setBattery(new RandomNumberGenerator().generateRandom(start, end));
        fogNode.setStorage(new RandomNumberGenerator().generateRandom(start, end));
    }
}