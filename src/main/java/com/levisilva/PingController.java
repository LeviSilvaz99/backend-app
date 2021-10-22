package com.levisilva;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("DEU BOM PATRAO", HttpStatus.OK);
    }
}
