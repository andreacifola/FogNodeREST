package com.example.FogNodeREST3.solver;

import com.example.FogNodeREST3.entities.HeavyTask;
import com.example.FogNodeREST3.handler.GetStateHandler;
import com.example.FogNodeREST3.handler.InterruptionHandler;

import java.io.IOException;
import java.math.BigInteger;

public class HeavyTaskSolver {
    GetStateHandler getStateHandler = new GetStateHandler();

    /**
     * this method returns the factorial of the given number n. during each iteration, it checks for the interruption flag.
     * @param heavyTask : task
     * @param n : number
     * @param partial : keeps track of the result at each step of the for loop
     * @param last : keeps track of the index i
     * @param midd_id : task id
     * @return heavy task
     */
    public HeavyTask factorial(HeavyTask heavyTask, int n, BigInteger partial, int last, int midd_id){

        HeavyTask res = heavyTask;

        int i;
        BigInteger fact = partial;  //setto il risultato come il parziale eventuale di una esecuzione precedente
        for(i = last+1; i<=n; i++){

            //controllo interruzione
            boolean flag = InterruptionHandler.getInstance().getFlagByTask(heavyTask.getID());
            if(flag) {
                //interruption
                System.out.println("job da interrompere");
                res.setResponse(null);
                res.setLast(i-1);
                res.setPartial(fact);
                return res;
            }

            fact = fact.multiply(BigInteger.valueOf(i));

            if (i % 100 == 0 && i != 0) {
                try {
                    getStateHandler.sendHeavyTaskState(heavyTask.getID(), fact, i, midd_id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("fattoriale di n = " + n + ": " + fact);
        res.setResponse(fact);
        res.setLast(i);
        res.setPartial(fact);
        return res;
    }
}