package com.atypon.MonteDB.controllers;


import com.atypon.MonteDB.services.WriteService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WriteController {


    @PostMapping( path = "database/write",consumes = {"application/json"})
    public  boolean
    write(@RequestParam String path,@RequestBody Object body) throws Exception
    {
        WriteService service=new WriteService();

       return service.write(path,body);

    }

}
