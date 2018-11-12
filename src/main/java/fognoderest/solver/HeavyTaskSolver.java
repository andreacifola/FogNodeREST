package fognoderest.solver;

import fognoderest.entities.HeavyTask;
import fognoderest.handler.GetStateHandler;
import fognoderest.handler.InterruptionHandler;

import java.io.IOException;
import java.math.BigInteger;

public class HeavyTaskSolver {
    GetStateHandler getStateHandler = new GetStateHandler();

    public BigInteger factorial(HeavyTask heavyTask, int n, BigInteger partial, int last, int midd_id){
        int i;
        BigInteger fact = partial;  //setto il risultato come il parziale eventuale di una esecuzione precedente
        for(i = last+1; i<=n; i++){

            //controllo interruzione
            boolean flag = InterruptionHandler.getInstance().getFlagByTask(heavyTask.getID());
            if(flag){
                //interruption
                System.out.println("job da interrompere");
                return null;
            }

            fact = fact.multiply(BigInteger.valueOf(i));

            try {
                getStateHandler.sendHeavyTaskState(heavyTask.getID(), fact, i, midd_id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fattoriale di n = " + n + ": " + fact);
        return fact;
    }
}