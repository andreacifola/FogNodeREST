package fognoderest.rest;

import fognoderest.entities.HeavyTask;
import fognoderest.solver.HeavyTaskSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "heavy")
public class HeavyTaskService {

    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    public ResponseEntity<HeavyTask> solveHeavyTask(@PathVariable int id, @RequestBody HeavyTask heavyTask, HttpServletResponse response) throws IOException, InterruptedException {
        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("heavyTask Received - NODE");
        System.out.println(id);

        Thread t = new Thread(() -> {

            HeavyTaskSolver solver = new HeavyTaskSolver();
            heavyTask.setResponse(solver.factorial(heavyTask, heavyTask.getN(), heavyTask.getPartial(), heavyTask.getLast(), id));
        });
        t.start();
        t.join();
        System.out.println("heavyTask Eseguito. Il fattoriale è : " + heavyTask.getResponse());

        return new ResponseEntity<>(heavyTask, HttpStatus.OK);
    }
}
