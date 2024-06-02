package com.dyot.app.services;

import java.util.List;

import com.dyot.app.dto.EquipoResponse;
import com.dyot.app.dto.EquipoResultsResponse;
import com.dyot.app.entities.EquipoRest;

public interface EquipoService {

	public List<EquipoResponse> findAll();

	public EquipoResponse findById(Integer id);
	
	public List<EquipoResultsResponse> findByDivisionId(Integer id);

	public int updateTeam(EquipoResponse equipoResponse);
}
