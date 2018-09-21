package fognoderest.rest;

import fognoderest.entities.HeavyTask;
import fognoderest.utils.ResponseWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "heavy")
public class HeavyTaskService {

    ResponseWriter responseWriter = new ResponseWriter();

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<HeavyTask> solveHeavyTask(@RequestBody HeavyTask heavyTask, HttpServletResponse response) throws IOException {

        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("heavyTask Received - NODE");

        //TODO GESTISCI TASK
        //heavyTask.setResponse(2019L);
        HeavyTaskSolver solver = new HeavyTaskSolver();
        solver.fibonacci(heavyTask.getN());
        System.out.println("heavyTask Eseguito");

        return new ResponseEntity<>(heavyTask, HttpStatus.OK);
    }
}
