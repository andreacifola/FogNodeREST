package fognoderest.solver;

import fognoderest.entities.LightTask;
import fognoderest.handler.GetStateHandler;

import java.io.IOException;


public class LightTaskSolver {

    GetStateHandler getStateHandler = new GetStateHandler();

    public String CaesarCode(LightTask lightTask) throws IOException {
        String encrypted = "";
        String toEncrypt = lightTask.getToEncrypt();
        for (int i = 0; i < toEncrypt.length(); i++) {
            char letter = toEncrypt.charAt(i);
            if (Character.isLetter(letter)) {
                letter += 3;
            }
            encrypted += letter;

            getStateHandler.sendLightTaskState(i, encrypted, lightTask.getID());
            if (i % 100 == 0 && i != 0) {
                //getStateHandler.sendLightTaskState(i, encrypted, lightTask.getID());
            }
        }
        return encrypted;
    }

    /*public String hash(LightTask task){
        String encrypted = UtilityMD5.stringByHashingPassword(task.getToEncrypt());
        return encrypted;
    }*/
}