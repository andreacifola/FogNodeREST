package fognoderest.Solver;

public class HeavyTaskSolver {

    public long fibonacci(long n){
        if(n<=1)
            return n;
        return fibonacci(n-1)+fibonacci(n-2);
    }
}
