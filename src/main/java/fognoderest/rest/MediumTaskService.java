package fognoderest.rest;

import fognoderest.entities.MediumTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "medium")
public class MediumTaskService {

    /*@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@RequestBody MediumTask mediumTask) {

        //TODO GESTISCI LA RISOLUZIONE DEL TASK

        return new ResponseEntity<>(mediumTask, HttpStatus.OK);
    }*/

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> solveMediumTask(@RequestBody MediumTask mediumTask) {

        return new ResponseEntity<>("MEDIUM TASK RICEVUTO DAL NODO", HttpStatus.OK);
    }
}
