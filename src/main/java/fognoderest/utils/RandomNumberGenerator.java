package fognoderest.utils;

import java.util.concurrent.ThreadLocalRandom;


public class RandomNumberGenerator {

    public Integer generateRandom(Integer start, Integer end){
        return ThreadLocalRandom.current().nextInt(start, end+1);
    }

    public Double generateRandom(Double start, Double end) {
        return ThreadLocalRandom.current().nextDouble(start, end+1);
    }
}
