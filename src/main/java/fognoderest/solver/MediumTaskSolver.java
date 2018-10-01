package fognoderest.solver;

import fognoderest.entities.MediumTask;
import fognoderest.handler.GetStateHandler;

import java.io.IOException;

public class MediumTaskSolver {

    public long count(MediumTask task) throws IOException {
        Long start = System.currentTimeMillis();
        GetStateHandler getStateHandler = new GetStateHandler();

        for (Integer i = 0; i < 10000000; i++) {
            if (i%1000 == 0 && i != 0)
                getStateHandler.sendMediumTaskState(i, System.currentTimeMillis()-start, task.getID());

            if (task.getNumber() == i)
                break;
        }
        Long time = System.currentTimeMillis() - start;
        System.out.println("mediumTask completato in" + time);
        return time;
    }
}