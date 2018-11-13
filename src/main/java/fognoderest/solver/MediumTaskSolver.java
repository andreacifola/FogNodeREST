package fognoderest.solver;

import fognoderest.entities.MediumTask;
import fognoderest.handler.GetStateHandler;
import fognoderest.handler.InterruptionHandler;

import java.io.IOException;

public class MediumTaskSolver {

    public MediumTask count(MediumTask mediumTask, Integer state, Long currentTime, int midd_id) throws IOException {
        GetStateHandler getStateHandler = new GetStateHandler();
        int i;

        MediumTask res = mediumTask;

        //recupero il tempo che eventualmente può essere legato ad una esecuzione precedente
        Long time = mediumTask.getCurrentTime();

        Long start = System.currentTimeMillis();

        for (i = state+1; i < 1000000; i++) {

            //controllo interruzione
            boolean flag = InterruptionHandler.getInstance().getFlagByTask(mediumTask.getID());
            if(flag){
                //interruption
                System.out.println("job da interrompere");
                res.setTime(0);
                res.setState(i);
                return res;
            }

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

        res.setTime(time);
        res.setState(i);
        return res;
    }
}