package fognoderest.solver;

import fognoderest.entities.LightTask;
import fognoderest.handler.GetStateHandler;

import java.io.IOException;


public class LightTaskSolver {

    /*
     * Questa funzione cifra una stringa con il "cifrario di Cesare". Trasmette come stato la cifra i-esima
     * che ha cifrato. Il ciclo di cifratura inizierà da tale indice +1, per tale motivo ii lightTask devono
     * essere inizializzati con stato pari a -1
     */
    public String CaesarCode(LightTask lightTask, Integer loopCount) throws IOException {
        GetStateHandler getStateHandler = new GetStateHandler();
        String toEncrypt = lightTask.getToEncrypt();
        String encrypted;

        if(loopCount == -1)
            encrypted = "";
        else
            encrypted = lightTask.getEncrypted();

        int i;
        for (i = loopCount+1; i < toEncrypt.length(); i++) {
            char letter = toEncrypt.charAt(i);
            if (Character.isLetter(letter)) {
                letter += 3;
            }
            encrypted += letter;

            //trasmetto lo stato al middleware
            if (i % 100 == 0 && i != 0) {
                getStateHandler.sendLightTaskState(i, encrypted, lightTask.getID());
            }

            if(i == toEncrypt.length()-1)
                System.out.println(encrypted);
        }
        return encrypted;
    }
}