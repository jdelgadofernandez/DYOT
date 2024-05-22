package com.dyot.app.services.impl;

import com.dyot.app.dto.DivisionResponse;
import com.dyot.app.entities.DivisionRest;
import com.dyot.app.mapper.DivisionMapper;
import com.dyot.app.services.DivisionService;
import com.dyot.app.services.EquipoService;
import com.dyot.app.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionMapper divisionMapper;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private MatchService matchService;

    @Override
    public List<DivisionResponse> findBySeasonId(Integer id) {
        List<DivisionRest> divisionRestList = divisionMapper.findBySeasonId(id);
        List<DivisionResponse> divisionResponseList = new ArrayList<>();
        for (DivisionRest divisionRest : divisionRestList) {
           DivisionResponse response = new DivisionResponse();
           response.setDivisionId(divisionRest.getDivisionId());
           response.setName(divisionRest.getName());
           response.setTemporadaId(divisionRest.getTemporadaId());
           response.setTeams(equipoService.findByDivisionId(divisionRest.getDivisionId()));
           response.setMatches(matchService.matchListByDivisionId(divisionRest.getDivisionId(),divisionRest.getName()));
           divisionResponseList.add(response);
        }

        return divisionResponseList;
    }

    public DivisionResponse findById(Integer id){
        DivisionResponse response = new DivisionResponse();
        DivisionRest divisionRest = divisionMapper.findById(id);
        response.setDivisionId(divisionRest.getDivisionId());
        response.setName(divisionRest.getName());
        response.setTemporadaId(divisionRest.getTemporadaId());
        response.setTeams(equipoService.findByDivisionId(divisionRest.getDivisionId()));
        response.setMatches(matchService.matchListByDivisionId(divisionRest.getDivisionId(),divisionRest.getName()));
        return response;
    }
}
