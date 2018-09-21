package fognoderest.rest;

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

    /*@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@RequestBody MediumTask mediumTask) {

        //TODO GESTISCI LA RISOLUZIONE DEL TASK

        return new ResponseEntity<>(mediumTask, HttpStatus.OK);
    }*/

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MediumTask> solveMediumTask(@RequestBody MediumTask mediumTask, HttpServletResponse response) throws IOException {

        //responseWriter.sendResponse("Processing Task...",response);
        System.out.println("Task Received - NODE");

        return new ResponseEntity<>(mediumTask, HttpStatus.OK);
    }
}
