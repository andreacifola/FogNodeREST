package com.example.FogNodeREST3.solver;

import com.example.FogNodeREST3.entities.HeavyTask;
import com.example.FogNodeREST3.handler.GetStateHandler;

import java.io.IOException;
import java.math.BigInteger;

public class HeavyTaskSolver {
    GetStateHandler getStateHandler = new GetStateHandler();

    public BigInteger factorial(HeavyTask heavyTask, int n, BigInteger partial, int last, int midd_id){
        int i;
        BigInteger fact = partial;  //setto il risultato come il parziale eventuale di una esecuzione precedente
        for(i = last+1; i<=n; i++){
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