package com.example.FogNodeREST3.solver;

import com.example.FogNodeREST3.entities.MediumTask;
import com.example.FogNodeREST3.handler.GetStateHandler;

import java.io.IOException;

public class MediumTaskSolver {

    public long count(MediumTask mediumTask, Integer state, Long currentTime, int midd_id) throws IOException {
        GetStateHandler getStateHandler = new GetStateHandler();
        int i;

        //recupero il tempo che eventualmente può essere legato ad una esecuzione precedente
        Long time = mediumTask.getCurrentTime();

        Long start = System.currentTimeMillis();

        for (i = state+1; i < 1000000; i++) {
            if (i%1000 == 0 && i != 0)
                getStateHandler.sendMediumTaskState(i, System.currentTimeMillis()-start, mediumTask.getID(), midd_id);

            if (mediumTask.getNumber() == i)
                break;
        }

        //lo aggiorno con il tempo della esecuzione attuale
        time = time + System.currentTimeMillis() - start;

        //Long time = System.currentTimeMillis() - start;

        if(mediumTask.getNumber() == i)
            System.out.println("mediumTask completato in " + time + " msec");
        return time;
    }
}