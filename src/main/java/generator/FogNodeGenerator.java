package generator;


import fognoderest.entities.Node;
import fognoderest.utils.RandomNumberGenerator;

public class FogNodeGenerator {

    public Node spawnFogNode(Integer id) {
        Node node = new Node();
        Integer randomNumber = new RandomNumberGenerator().generateRandom(1,3);
        switch (randomNumber) {
            case 1:
                generateFogNodeParameters(node, id, "LIGHT", 1, 3);
            case 2:
                generateFogNodeParameters(node, id, "MEDIUM", 4, 7);
            case 3:
                generateFogNodeParameters(node, id, "HEAVY", 8, 10);
        }
        return node;
    }

    private void generateFogNodeParameters(Node node, Integer id, String type, Integer start, Integer end) {
        node.setId(id);
        node.setType(type);
        node.setRam(new RandomNumberGenerator().generateRandom(start, end));
        node.setCpu(new RandomNumberGenerator().generateRandom(start, end));
        node.setBattery(new RandomNumberGenerator().generateRandom(start, end));
        node.setStorage(new RandomNumberGenerator().generateRandom(start, end));
    }
}
