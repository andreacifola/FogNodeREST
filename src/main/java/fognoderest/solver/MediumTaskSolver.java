package fognoderest.solver;

import fognoderest.entities.MediumTask;
import fognoderest.handler.GetStateHandler;

import java.io.IOException;

public class MediumTaskSolver {

    public long count(MediumTask mediumTask, Integer state, Long currentTime) throws IOException {
        GetStateHandler getStateHandler = new GetStateHandler();
        int i;

        //recupero il tempo che eventualmente può essere legato ad una esecuzione precedente
        Long time = mediumTask.getTime();

        Long start = System.currentTimeMillis();

        for (i = state+1; i < 1000000; i++) {
            if (i%1000 == 0 && i != 0)
                getStateHandler.sendMediumTaskState(i, System.currentTimeMillis()-start, mediumTask.getID());

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