package com.dyot.app.controllers;

import com.dyot.app.dto.SeasonResponse;
import com.dyot.app.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/season")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;


    @PostMapping("/fullcreation")
    public int fullcreation(@RequestBody SeasonResponse seasonResponse,@RequestParam("division")Integer division,@RequestParam("equipos")Integer equipos){

        return seasonService.insertSeason(seasonResponse,division,equipos);
    }

    @PostMapping("/clean")
    public void cleanDatabase() {
        seasonService.cleanDatabase();
    }

}
