package fognoderest.Solver;

import fognoderest.entities.MediumTask;

public class MediumTaskSolver {

    public void count(MediumTask task) {
        int cont = 0, i;
        long start = System.currentTimeMillis();
        for (i = 0; i < 10000000; i++) {
            cont++;
            if (task.getNumber() == cont)
                break;
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("mediumTask completato in" + time);
    }
}