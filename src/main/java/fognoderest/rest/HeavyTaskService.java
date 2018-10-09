package fognoderest.rest;

import fognoderest.entities.HeavyTask;
import fognoderest.solver.HeavyTaskSolver;
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

        HeavyTaskSolver solver = new HeavyTaskSolver();

        if (heavyTask.getLast()==0){
            // inizia il job da 0

            heavyTask.setResponse(solver.factorial(heavyTask.getN()));
        }else{
            //il job è stato precedentemente interrotto quindi riprendi il calcolo da last

            heavyTask.setResponse(solver.factorial(heavyTask.getN(),heavyTask.getPartial(),heavyTask.getLast()));
        }

        System.out.println("heavyTask Eseguito");
        return new ResponseEntity<>(heavyTask, HttpStatus.OK);

    }


}
