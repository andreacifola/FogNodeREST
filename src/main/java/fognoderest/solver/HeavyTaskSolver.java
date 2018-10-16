package fognoderest.solver;

import fognoderest.entities.HeavyTask;
import fognoderest.handler.GetStateHandler;

import java.io.IOException;
import java.math.BigInteger;

public class HeavyTaskSolver {
    GetStateHandler getStateHandler = new GetStateHandler();
/*
    public BigInteger factorial (int n){

        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
        System.out.println("Job completato : "+fact);
        return fact;
    }

    public BigInteger factorial (int n, BigInteger partial, int last){

        BigInteger fact =partial;
        for (int i = 1; i <= last-1; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
        System.out.println("Job ripreso : "+fact);
        return fact;
    }
*/
    /*
    last = indice a cui si è arrivati
    partial = risultato parziale
    n = è il valore di cui calcolare il fattoriale
     */
    public BigInteger factorial(HeavyTask heavyTask, int n, BigInteger partial, int last){
        //todo porre partial = 1, last = 0 nel deviceTask e rendere la funzione generale
        int i;
        BigInteger fact = partial;  //setto il risultato come il parziale eventuale di una esecuzione precedente
        for(i = last+1; i<=n; i++){
            fact = fact.multiply(BigInteger.valueOf(i));

            try {
                getStateHandler.sendHeavyTaskState(heavyTask.getID(), fact, last);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fattoriale di n = " + n + ": " + fact);
        return fact;
    }
}