package fognoderest.rest;

import fognoderest.handler.InterruptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="interruption")
public class InterruptionService {

    /**
     * This method handles the interruption request.
     * @param id
     */
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<String> interruptTask(@PathVariable int id) {

        String res;
        boolean esito = InterruptionHandler.getInstance().interruptTask(id);
        if(esito) {
            res = "ACK";
            System.out.println("Interruption request completed - id: "+id+"\n");
        } else {
            res = "NACK";
            System.out.println("Interruption request rejected - id: "+id+"\n");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}