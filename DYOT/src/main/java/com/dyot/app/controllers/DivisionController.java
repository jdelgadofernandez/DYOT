package com.dyot.app.controllers;

import com.dyot.app.dto.DivisionResponse;
import com.dyot.app.services.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/division")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping("/retrieveBySeasonId")
    private List<DivisionResponse> retrieveBySeasonId(@RequestParam(value = "id") Integer id){

        return divisionService.findBySeasonId(id);
    }

}
