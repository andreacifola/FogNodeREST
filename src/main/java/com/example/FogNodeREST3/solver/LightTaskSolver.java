package com.example.FogNodeREST3.solver;

import com.example.FogNodeREST3.entities.LightTask;
import com.example.FogNodeREST3.handler.GetStateHandler;
import com.example.FogNodeREST3.handler.InterruptionHandler;

import java.io.IOException;


public class LightTaskSolver {

    /*
     * Questa funzione cifra una stringa con il "cifrario di Cesare". Trasmette come stato la cifra i-esima
     * che ha cifrato. Il ciclo di cifratura inizierà da tale indice +1, per tale motivo ii lightTask devono
     * essere inizializzati con stato pari a -1
     */
    public LightTask CaesarCode(LightTask lightTask, Integer loopCount, int midd_id) throws IOException {
        GetStateHandler getStateHandler = new GetStateHandler();

        LightTask res = lightTask;
        String toEncrypt = lightTask.getToEncrypt();
        String encrypted;

        if(loopCount == -1)
            encrypted = "";
        else
            encrypted = lightTask.getEncrypted();

        int i;


        for (i = loopCount+1; i < toEncrypt.length(); i++) {

            //controllo interruzione
            boolean flag = InterruptionHandler.getInstance().getFlagByTask(lightTask.getID());
            if(flag){
                //interruption
                System.out.println("job da interrompere");

                res.setLoopCount(i);
                res.setEncrypted(null);
                return res;
            }

            char letter = toEncrypt.charAt(i);

            if (Character.isLetter(letter)) {
                if(letter == 'x')
                    letter = 'a';
                else if(letter == 'y')
                    letter = 'b';
                else if(letter == 'z')
                    letter = 'c';
                else
                    letter += 3;
                encrypted += letter;
            }
            //trasmetto lo stato al middleware
            if (i % 100 == 0 && i != 0) {
                getStateHandler.sendLightTaskState(i, encrypted, lightTask.getID(), midd_id);
            }
        }
        res.setLoopCount(i);
        res.setEncrypted(encrypted);
        return res;
    }
}