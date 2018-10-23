package fognoderest.rest;

import fognoderest.entities.LightTask;
import fognoderest.solver.LightTaskSolver;
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
        //TODO inserire thread
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
}
