package com.atypon.MonteDB.controllers;

import com.atypon.MonteDB.services.ReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReadController {


    @GetMapping( path = "database/readFile")
    public <T> T readFile(@RequestParam String path) throws IOException {
        return ReadService.readFile(path);
    }

    @GetMapping( path = "database/readCollection")
    public <T> T readCollection(@RequestParam String path) throws IOException {
        return ReadService.readCollection(path);
    }
}
