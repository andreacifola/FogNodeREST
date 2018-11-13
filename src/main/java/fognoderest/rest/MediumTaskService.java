package fognoderest.rest;

import fognoderest.entities.MediumTask;
import fognoderest.handler.InterruptionHandler;
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
        //responseWriter.sendResponse("Processing Task...",response);

        System.out.println("mediumTask Received - NODE");
        mediumTask.setID(id);

        //task is added to interruption list
        InterruptionHandler.getInstance().addTaskToList(mediumTask);

        MediumTaskSolver solver = new MediumTaskSolver();
        MediumTask res = solver.count(mediumTask, mediumTask.getState(), mediumTask.getCurrentTime(), id);

        /*
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
*/
        if(mediumTask.getTime() != 0){
            mediumTask.setState(-2);
        }

        System.out.println("mediumTask Eseguito in " + mediumTask.getTime());

        //task is removed from interruption list
        InterruptionHandler.getInstance().removeTask(mediumTask.getID());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}