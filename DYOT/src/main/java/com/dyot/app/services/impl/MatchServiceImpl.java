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

    @Override
    public int updateMatch(Integer id, String resultado) {
        // Obtener partido por ID
        MatchRest partido = matchMapper.retrieveMatchById(id);


        // Obtener el resultado anterior
        String resultadoAnterior = partido.getResultado();
        if (!resultadoAnterior.equals("0-0")) {
            revertTeamStats(partido, resultadoAnterior);
        }

        // Actualizar resultado del partido
        matchMapper.updateResult(id, resultado);

        // Actualizar estadísticas basadas en el nuevo resultado
        updateTeamStats(partido, resultado);

        return 1;
    }

    private void revertTeamStats(MatchRest partido, String resultado) {
        String[] scores = resultado.split("-");
        int score1 = Integer.parseInt(scores[0]);
        int score2 = Integer.parseInt(scores[1]);

        // Variables para revertir las estadísticas
        int puntos1 = 0, victorias1 = 0, empates1 = 0, derrotas1 = 0;
        int puntos2 = 0, victorias2 = 0, empates2 = 0, derrotas2 = 0;

        if (score1 > score2) {
            puntos1 = -3;
            victorias1 = -1;
            derrotas2 = -1;
        } else if (score1 < score2) {
            puntos2 = -3;
            victorias2 = -1;
            derrotas1 = -1;
        } else {
            puntos1 = -1;
            puntos2 = -1;
            empates1 = -1;
            empates2 = -1;
        }

        matchMapper.updateTeamStats(partido.getEquipoid1(), partido.getDivisionId(), -score1, -score2, puntos1, victorias1, empates1, derrotas1);
        matchMapper.updateTeamStats(partido.getEquipoid2(), partido.getDivisionId(), -score2, -score1, puntos2, victorias2, empates2, derrotas2);
    }

    private void updateTeamStats(MatchRest partido, String resultado) {
        String[] scores = resultado.split("-");
        int score1 = Integer.parseInt(scores[0]);
        int score2 = Integer.parseInt(scores[1]);

        // Variables para actualizar las estadísticas
        int puntos1 = 0, victorias1 = 0, empates1 = 0, derrotas1 = 0;
        int puntos2 = 0, victorias2 = 0, empates2 = 0, derrotas2 = 0;

        if (score1 > score2) {
            puntos1 = 3;
            victorias1 = 1;
            derrotas2 = 1;
        } else if (score1 < score2) {
            puntos2 = 3;
            victorias2 = 1;
            derrotas1 = 1;
        } else {
            puntos1 = 1;
            puntos2 = 1;
            empates1 = 1;
            empates2 = 1;
        }

        matchMapper.updateTeamStats(partido.getEquipoid1(), partido.getDivisionId(), score1, score2, puntos1, victorias1, empates1, derrotas1);
        matchMapper.updateTeamStats(partido.getEquipoid2(), partido.getDivisionId(), score2, score1, puntos2, victorias2, empates2, derrotas2);
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
