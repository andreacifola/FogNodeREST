package fognoderest.solver;

import java.math.BigInteger;

public class HeavyTaskSolver {

    public BigInteger factorial (int n){

        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
        System.out.println("Job completato : "+fact);
        return fact;
    }

    public BigInteger factorial (int n,BigInteger partial,int last){

        BigInteger fact =partial;
        for (int i = 1; i <= last-1; i++)
            fact = fact.multiply(BigInteger.valueOf(i));
        System.out.println("Job ripreso : "+fact);
        return fact;
    }
}