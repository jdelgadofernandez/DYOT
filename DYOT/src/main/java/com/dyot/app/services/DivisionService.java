package com.dyot.app.services;

import com.dyot.app.dto.DivisionResponse;
import com.dyot.app.dto.EquipoResultsResponse;

import java.util.List;

public interface DivisionService {

   public List<DivisionResponse> findBySeasonId(Integer id);

   DivisionResponse findById(Integer id);

}
