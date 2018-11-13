package fognoderest.solver;

import fognoderest.entities.HeavyTask;
import fognoderest.handler.GetStateHandler;
import fognoderest.handler.InterruptionHandler;

import java.io.IOException;
import java.math.BigInteger;

public class HeavyTaskSolver {
    GetStateHandler getStateHandler = new GetStateHandler();

    public HeavyTask factorial(HeavyTask heavyTask, int n, BigInteger partial, int last, int midd_id){

        HeavyTask res = heavyTask;

        int i;
        BigInteger fact = partial;  //setto il risultato come il parziale eventuale di una esecuzione precedente
        for(i = last+1; i<=n; i++){

            //controllo interruzione
            boolean flag = InterruptionHandler.getInstance().getFlagByTask(heavyTask.getID());
            if(flag){
                //interruption

                try {
                    getStateHandler.sendHeavyTaskState(heavyTask.getID(), fact, i, midd_id);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("job da interrompere");
                res.setResponse(null);
                res.setLast(i);
                res.setPartial(fact);
                return res;
            }

            fact = fact.multiply(BigInteger.valueOf(i));

            //if (i % 100 == 0 && i != 0) {
                try {
                    getStateHandler.sendHeavyTaskState(heavyTask.getID(), fact, i, midd_id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
           // }
        }
        System.out.println("fattoriale di n = " + n + ": " + fact);
        res.setResponse(fact);
        res.setLast(i);
        res.setPartial(fact);
        return res;
    }
}