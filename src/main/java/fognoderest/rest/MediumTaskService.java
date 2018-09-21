package fognoderest.rest;

import fognoderest.Solver.MediumTaskSolver;
import fognoderest.entities.MediumTask;
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
@RequestMapping(path = "medium")
public class MediumTaskService {

    ResponseWriter responseWriter = new ResponseWriter();

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@RequestBody MediumTask mediumTask, HttpServletResponse response) throws IOException {

        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("mediumTask Received - NODE");

        //TODO GESTISCI TASK
        //mediumTask.setNumber(20);
        //mediumTask.setTime(2011419);
        MediumTaskSolver solver = new MediumTaskSolver();
        solver.count(mediumTask);
        System.out.println("mediumTask Eseguito");

        return new ResponseEntity<>(mediumTask, HttpStatus.OK);
    }
}
