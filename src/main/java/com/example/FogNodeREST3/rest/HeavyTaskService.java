package com.example.FogNodeREST3.rest;

import com.example.FogNodeREST3.solver.HeavyTaskSolver;
import com.example.FogNodeREST3.entities.HeavyTask;

import com.example.FogNodeREST3.handler.InterruptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "heavy")
public class HeavyTaskService {

    /**
     * this remote method is invoked by the middleware to send an heavy task to the fog node.
     */
    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    public ResponseEntity<HeavyTask> solveHeavyTask(@PathVariable int id, @RequestBody HeavyTask heavyTask, HttpServletResponse response) throws IOException, InterruptedException {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("heavyTask Received - NODE");
        System.out.println(id);
        heavyTask.setID(id);
        System.out.println("heavy task: "+heavyTask.getID());

        //task is added to interruption list
        InterruptionHandler.getInstance().addTaskToList(heavyTask);

        HeavyTaskSolver solver = new HeavyTaskSolver();
        HeavyTask res = solver.factorial(heavyTask, heavyTask.getN(), heavyTask.getPartial(), heavyTask.getLast(), id);

        //se il valore di ritorno non è nullo, il task è stato completato
        if(heavyTask.getResponse() != null){
            heavyTask.setLast(-2);

            //task is removed from interruption list
            InterruptionHandler.getInstance().removeTask(heavyTask.getID());
        }

//        System.out.println(InterruptionHandler.getInstance().getFlagByTask(heavyTask.getID()));

        System.out.println("heavyTask Eseguito. Il fattoriale è : " + heavyTask.getResponse() + "\n");


        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
