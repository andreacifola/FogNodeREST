package com.example.FogNodeREST3.rest;

import com.example.FogNodeREST3.solver.LightTaskSolver;
import com.example.FogNodeREST3.entities.LightTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "light")
public class LightTaskService {

    //ResponseWriter responseWriter = new ResponseWriter();

    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    public ResponseEntity<LightTask> solveLightTask(@PathVariable int id, @RequestBody LightTask lightTask, HttpServletResponse response) throws IOException, InterruptedException {
        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("lightTask Received - NODE");

        Thread t = new Thread(() -> {

            LightTaskSolver solver = new LightTaskSolver();
            try {
                lightTask.setEncrypted(solver.CaesarCode(lightTask, lightTask.getLoopCount(), id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.println("lightTask Eseguito. Testo cifrato :" + lightTask.getEncrypted());

        return new ResponseEntity<>(lightTask, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String hello() {
        return "HELLO WORLD - I'm a fogNode!";
    }
}
