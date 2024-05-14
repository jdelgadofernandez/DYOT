package com.dyot.app.controllers;

import com.dyot.app.dto.PlayerActiveTeamResponse;
import com.dyot.app.dto.PlayerResponse;
import com.dyot.app.services.EquipoService;
import com.dyot.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private PlayerService playerService;

	@GetMapping("retrieveAll")
	public List<PlayerResponse> findAll(){
		
		return playerService.findAll();
	}

	@GetMapping("retrievePlayerById")
	public PlayerResponse findById(@RequestParam("id")Integer id){

		return playerService.findById(id);
	}

	@GetMapping("retrievePlayerByTeamId")
	public List<PlayerActiveTeamResponse> findByTeamId(@RequestParam("teamId")Integer teamId){

		return playerService.findByTeamId(teamId);
	}
	
}
