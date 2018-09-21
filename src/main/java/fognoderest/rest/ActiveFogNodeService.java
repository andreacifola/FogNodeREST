package fognoderest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "active")
public class ActiveFogNodeService {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<String> fogNodeRegistration() {

        return new ResponseEntity<String>("ACK", HttpStatus.OK);
    }
}
