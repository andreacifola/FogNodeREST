package fognoderest.rest;

import fognoderest.entities.MediumTask;
import fognoderest.solver.MediumTaskSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "medium")
public class MediumTaskService {
    //ResponseWriter responseWriter = new ResponseWriter();

    @RequestMapping(path = "{id}", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@PathVariable int id, @RequestBody MediumTask mediumTask, HttpServletResponse response) throws IOException, InterruptedException {
        //TODO inserire thread
        //responseWriter.sendResponse("Processing Task...",response);

        System.out.println("mediumTask Received - NODE");

        Thread t = new Thread(() -> {

            MediumTaskSolver solver = new MediumTaskSolver();
            try {
                mediumTask.setTime(solver.count(mediumTask, mediumTask.getState(), mediumTask.getCurrentTime(), id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.println("mediumTask Eseguito in " + mediumTask.getTime());

        return new ResponseEntity<>(mediumTask, HttpStatus.OK);
    }
}