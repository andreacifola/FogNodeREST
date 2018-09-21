package fognoderest.solver;

public class HeavyTaskSolver {

    public Long SolveHeavyTask() {
        //calcola la sequenza di fibonacci per n = 45

        System.out.println("Processing Heavy Task...");
        Long n = 45L;
        return fib(n);
    }

    Long fib(Long n)
    {
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

}
