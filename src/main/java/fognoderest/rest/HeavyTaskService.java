package fognoderest.rest;

import fognoderest.entities.HeavyTask;
import fognoderest.handler.InterruptionHandler;
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
        heavyTask.setID(id);
        System.out.println("heavy task: "+heavyTask.getID());

        Thread t = new Thread(() -> {
            HeavyTaskSolver solver = new HeavyTaskSolver();
            heavyTask.setResponse(solver.factorial(heavyTask, heavyTask.getN(), heavyTask.getPartial(), heavyTask.getLast(), id));
        });

        //task is added to interruption list
        InterruptionHandler.getInstance().addTaskToList(heavyTask);

        t.start();
        t.join();

        System.out.println(InterruptionHandler.getInstance().getFlagByTask(heavyTask.getID()));

        System.out.println("heavyTask Eseguito. Il fattoriale è : " + heavyTask.getResponse());

        //task is removed from interruption list
        InterruptionHandler.getInstance().removeTask(heavyTask.getID());

        return new ResponseEntity<>(heavyTask, HttpStatus.OK);
    }
}
