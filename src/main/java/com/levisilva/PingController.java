package com.levisilva;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class PingController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void ping(){
        System.out.println("TUDO FUNCIONANDO");
    }
}
