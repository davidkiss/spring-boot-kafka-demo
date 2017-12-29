package com.kaviddiss.bootkafka.web;

import com.kaviddiss.bootkafka.model.Greetings;
import com.kaviddiss.bootkafka.service.GreetingsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
    private final GreetingsService greetingsService;

    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void hello(@RequestParam("message") String message) {
        Greetings greetings = Greetings.builder().message(message).build();

        greetingsService.sendGreeting(greetings);
    }
}
