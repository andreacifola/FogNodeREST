package fognoderest.rest;

import fognoderest.entities.LightTask;
import fognoderest.handler.InterruptionHandler;
import fognoderest.solver.LightTaskSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "light")
public class LightTaskService {

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

        /*
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
        */

        if(lightTask.getEncrypted() != null){
            lightTask.setLoopCount(-2);
        }

        System.out.println("lightTask Eseguito. Testo cifrato :" + lightTask.getEncrypted() + "\n");

        //task is removed from interruption list
        InterruptionHandler.getInstance().removeTask(lightTask.getID());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
