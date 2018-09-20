package fognoderest.rest;

import fognoderest.entities.HeavyTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "heavy")
public class HeavyTaskService {

    /*@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<HeavyTask> solveHeavyTask(@RequestBody HeavyTask heavyTask) {

        //TODO GESTISCI LA RISOLUZIONE DEL TASK

        return new ResponseEntity<>(heavyTask, HttpStatus.OK);
    }*/

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> solveHeavyTask(@RequestBody HeavyTask heavyTask) {
        return new ResponseEntity<>("HEAVY TASK RICEVUTO DAL NODO", HttpStatus.OK);
    }
}
