package com.example.FogNodeREST3.rest;

import com.example.FogNodeREST3.solver.LightTaskSolver;
import com.example.FogNodeREST3.entities.LightTask;
import com.example.FogNodeREST3.handler.InterruptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "light")
public class LightTaskService {

    /**
     * this remote method is invoked by the middleware to send a light task to the fog node.
     */
    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    public ResponseEntity<LightTask> solveLightTask(@PathVariable int id, @RequestBody LightTask lightTask, HttpServletResponse response) throws IOException, InterruptedException {
        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("lightTask Received - NODE");
        lightTask.setID(id);

        //task is added to interruption list
        InterruptionHandler.getInstance().addTaskToList(lightTask);

        LightTaskSolver solver = new LightTaskSolver();
        LightTask res = solver.CaesarCode(lightTask, lightTask.getLoopCount(), id);

        //se il valore di ritorno non è nullo, il task è stato completato
        if(lightTask.getEncrypted() != null){
            lightTask.setLoopCount(-2);

            //task is removed from interruption list
            InterruptionHandler.getInstance().removeTask(lightTask.getID());
        }

        System.out.println("lightTask Eseguito. Testo cifrato :" + lightTask.getEncrypted() + "\n");


        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String hello() {
        return "HELLO WORLD - I'm a fogNode!";
    }
}
