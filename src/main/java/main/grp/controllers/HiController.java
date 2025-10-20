package main.grp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/hi")
public class HiController {
    @GetMapping
    public ResponseEntity<String> sayHi() {
        return ResponseEntity.ok("HI! <br><pr>" + LocalDateTime.now());
    }
}
