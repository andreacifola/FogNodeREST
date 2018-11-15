package com.example.FogNodeREST3.rest;

import com.example.FogNodeREST3.solver.MediumTaskSolver;
import com.example.FogNodeREST3.entities.MediumTask;
import com.example.FogNodeREST3.handler.InterruptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "medium")
public class MediumTaskService {
    //ResponseWriter responseWriter = new ResponseWriter();

    /**
     * this remote method is invoked by the middleware to send a medium task to the fog node.
     */
    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@PathVariable int id, @RequestBody MediumTask mediumTask, HttpServletResponse response) throws IOException, InterruptedException {
        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("mediumTask Received - NODE");
        mediumTask.setID(id);

        //task is added to interruption list
        InterruptionHandler.getInstance().addTaskToList(mediumTask);

        MediumTaskSolver solver = new MediumTaskSolver();
        MediumTask res = solver.count(mediumTask, mediumTask.getState(), mediumTask.getCurrentTime(), id);

        //se il valore di ritorno non è nullo, il task è stato completato
        if(mediumTask.getTime() != 0){
            mediumTask.setState(-2);

            //task is removed from interruption list
            InterruptionHandler.getInstance().removeTask(mediumTask.getID());
        }

        System.out.println("mediumTask Eseguito in " + mediumTask.getTime() + " msec\n");


        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}