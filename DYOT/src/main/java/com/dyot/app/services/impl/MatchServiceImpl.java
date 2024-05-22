package com.dyot.app.services.impl;

import com.dyot.app.dto.MatchResponse;
import com.dyot.app.entities.MatchRest;
import com.dyot.app.mapper.MatchMapper;
import com.dyot.app.services.DivisionService;
import com.dyot.app.services.EquipoService;
import com.dyot.app.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;


    @Autowired
    private EquipoService equipoService;

    @Override
    public MatchResponse retrieveMatchById(Integer id, String name) {
        return this.matchRestToResponse(id, name);
    }

    @Override
    public List<MatchResponse> matchListByDivisionId(Integer id, String name) {
        List<MatchRest> matchRestList = matchMapper.matchListByDivisionId(id);
        List<MatchResponse> responseList = new ArrayList<>();
        for( MatchRest matchRest : matchRestList){
            MatchResponse response = this.matchRestToResponse(matchRest.getPartidoId(), name);
            responseList.add(response);

        }

        return responseList;
    }

    @Override
    public List<MatchResponse> matchNextFourMatches(String name) {
        List<MatchRest> matchRestList = matchMapper.matchNextFourMatches();
        List<MatchResponse> responseList = new ArrayList<>();
        for( MatchRest matchRest : matchRestList){
            MatchResponse response = this.matchRestToResponse(matchRest.getPartidoId(),name);
            responseList.add(response);
        }

        return responseList;
    }

    private MatchResponse matchRestToResponse(Integer id, String name){
        MatchResponse response = new MatchResponse();
        MatchRest matchRest = matchMapper.retrieveMatchById(id);
        response.setDivisionid(matchRest.getDivisionId());
        response.setPartidoid(matchRest.getPartidoId());
        response.setDivisionName(name);
        response.setEquipo1(equipoService.findById(matchRest.getEquipoid1()));
        response.setEquipo2(equipoService.findById(matchRest.getEquipoid2()));
        response.setFechapartido(matchRest.getFechapartido());
        response.setJornada(matchRest.getJornada());
        response.setResultado(matchRest.getResultado());
        return response;
    }
}
