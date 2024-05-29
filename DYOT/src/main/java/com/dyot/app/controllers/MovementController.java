package com.dyot.app.controllers;

import com.dyot.app.dto.MovementResponse;
import com.dyot.app.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping("/last")
    public List<MovementResponse> retrieveLast10Movements(){

        return movementService.retrieveLastTen();
    }


    @GetMapping("/team")
    public List<MovementResponse> retrieveByTeamId(@RequestParam("teamid")Integer teamId){

        return movementService.retrieveByTeamId(teamId);
    }

    @GetMapping("/player")
    public List<MovementResponse> retrieveByPlayerId(@RequestParam("playerid")Integer playerId){

        return movementService.retrieveByPlayerId(playerId);
    }
}
