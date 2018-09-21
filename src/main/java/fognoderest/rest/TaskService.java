package fognoderest.rest;

import fognoderest.entities.HeavyTask;
import fognoderest.entities.LightTask;
import fognoderest.entities.MediumTask;
import fognoderest.solver.LightTaskSolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "taskExecute")
public class TaskService {

    //TODO: definire i path. Spostare nei taskService singoli??

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> solveLightTask(@RequestBody LightTask task) {

        LightTaskSolver solver = new LightTaskSolver();
        solver.hash(task);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
/*
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> solveMediumTask(@RequestBody MediumTask task) {

        MediumTaskSolver solver = new MediumTaskSolver();
        solver.count(task);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> solveHeavyTask(@RequestBody HeavyTask task) {

        HeavyTaskSolver solver = new HeavyTaskSolver();
        solver.fibonacci(task.getN());
        return new ResponseEntity<>("", HttpStatus.OK);
    }
*/
}