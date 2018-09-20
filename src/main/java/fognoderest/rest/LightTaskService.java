package fognoderest.rest;

import fognoderest.entities.LightTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "light")
public class LightTaskService {

    /*@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<LightTask> solveLightTask(@RequestBody LightTask lightTask) {

        return new ResponseEntity<>(lightTask, HttpStatus.OK);
    }*/

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<String> solveLightTask(@RequestBody LightTask lightTask) {

        return new ResponseEntity<>("LIGHT TASK RICEVUTO DAL NODO", HttpStatus.OK);
    }

}
