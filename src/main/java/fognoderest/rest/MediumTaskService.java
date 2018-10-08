package fognoderest.rest;

import fognoderest.entities.MediumTask;
import fognoderest.solver.MediumTaskSolver;
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

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@RequestBody MediumTask mediumTask, HttpServletResponse response) throws IOException {

        System.out.println("mediumTask Received - NODE" + mediumTask.getID());

        MediumTaskSolver solver = new MediumTaskSolver();
        mediumTask.setTime(solver.count(mediumTask));
        System.out.println("mediumTask Eseguito");

        return new ResponseEntity<>(mediumTask, HttpStatus.OK);
    }
}
