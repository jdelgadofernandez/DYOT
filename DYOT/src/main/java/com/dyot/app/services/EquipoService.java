package com.dyot.app.services;

import java.util.List;

import com.dyot.app.dto.EquipoResponse;
import com.dyot.app.dto.EquipoResultsResponse;
import com.dyot.app.entities.EquipoRest;

public interface EquipoService {

    List<EquipoResponse> findAll();

    EquipoResponse findById(Integer id);

    List<EquipoResultsResponse> findByDivisionId(Integer id);

    int updateTeam(EquipoResponse equipoResponse);

    void createFromSeason(int equipos,Integer divId, Integer seasonId);

}
