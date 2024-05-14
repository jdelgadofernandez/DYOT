package com.dyot.app.controllers;

import java.util.List;

import com.dyot.app.dto.EquipoResultsResponse;
import com.dyot.app.dto.PlayerResponse;
import com.dyot.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dyot.app.dto.EquipoResponse;
import com.dyot.app.services.EquipoService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/team")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private PlayerService playerService;

	@GetMapping("retrieveAll")
	public List<EquipoResponse> findAll(){
		
		return equipoService.findAll();
	}

	@GetMapping("retrieveByDivisionId/")
	public List<EquipoResultsResponse> findByDivisionId(@RequestParam("id") Integer id){

		return  equipoService.findByDivisionId(id);
	}
	
}
