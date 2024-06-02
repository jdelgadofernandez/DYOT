package com.dyot.app.controllers;


import com.dyot.app.dto.MatchResponse;
import com.dyot.app.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

        @GetMapping(value = "/divisionmatches")
    public List<MatchResponse> matchListByDivisionId(@RequestParam(value = "id") Integer id, @RequestParam(value ="name") String name){

        return matchService.matchListByDivisionId(id, name);
    }

    @GetMapping(value = "/match")
    public MatchResponse matchId(@RequestParam(value = "id") Integer id, @RequestParam(value ="name") String name){

        return matchService.retrieveMatchById(id, name);
    }

    @PutMapping(value = "/updateResult")
    public int updateResult(@RequestParam Integer partidoId, @RequestParam String resultado) {
        return matchService.updateMatch(partidoId, resultado);

    }
}
