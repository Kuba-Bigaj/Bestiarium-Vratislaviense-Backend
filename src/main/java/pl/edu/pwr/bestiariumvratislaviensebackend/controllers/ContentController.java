package pl.edu.pwr.bestiariumvratislaviensebackend.controllers;

import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @GetMapping("/cryptids")
    public ResponseEntity<String> get_story()
    {
        return new ResponseEntity<>("Story goes here!", HttpStatus.OK);
    }
}
