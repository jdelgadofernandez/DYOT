package com.dyot.app.services;

import java.util.List;

import com.dyot.app.dto.EquipoResponse;
import com.dyot.app.dto.EquipoResultsResponse;

public interface EquipoService {

	public List<EquipoResponse> findAll();
	
	public List<EquipoResultsResponse> findByDivisionId(Integer id);
}
