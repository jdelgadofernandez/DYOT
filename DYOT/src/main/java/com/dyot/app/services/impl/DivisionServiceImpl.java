package com.dyot.app.services.impl;

import com.dyot.app.dto.DivisionResponse;
import com.dyot.app.entities.DivisionRest;
import com.dyot.app.entities.PartidoDivisionEquipoRest;
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
    public List<DivisionResponse> findAll() {
        List<DivisionRest> divisionRestList = divisionMapper.findAll();
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
    @Override
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

    @Override
    public void createFromSeason(Integer id,int number,int equipo){
        for(int i = 0;i<number;i++){
            DivisionRest rest = new DivisionRest();
            rest.setName("Division "+(i+1));
            rest.setTemporadaId(id);
           divisionMapper.createDivision(rest);
           Integer divId = divisionMapper.getLastInsertedId();
           equipoService.createFromSeason(equipo,divId,id);
           crearJornadas(divId,divisionMapper.findTeamIdListByDivisionId(divId));
        }

    }


    private void crearJornadas(Integer divId, List<Integer> teamsId){

        //Meter id ficticio si hay equipos impares
        if (teamsId.size() % 2 != 0) {
            teamsId.add(-1);
        }

        int jornadas = (teamsId.size()-1);
        int mitadTamano = teamsId.size()/2;

        List<Integer> copiaIds = new ArrayList<>(teamsId);
        copiaIds.remove(0);

        int tamanoEquipo = copiaIds.size();

        for (int dia = 0; dia<jornadas;dia++){
            crearPartido(divId,copiaIds.get(dia%tamanoEquipo),teamsId.get(0),dia+1);

            for(int idx =1;idx<mitadTamano;idx++){
                Integer primerEquipo = (dia+idx)%tamanoEquipo;
                Integer segundoEquipo = (dia+tamanoEquipo-idx) % tamanoEquipo;
                crearPartido(divId,copiaIds.get(primerEquipo),copiaIds.get(segundoEquipo),dia+1);
            }
        }
    }

    private void crearPartido(Integer divId, Integer equipo1, Integer equipo2, int jornada){
        if (equipo1 == -1 || equipo2 == -1) {
            return; // Ignora los partidos con equipos ficticios
        }

        PartidoDivisionEquipoRest rest = new PartidoDivisionEquipoRest();
        rest.setDivisionId(divId);
        rest.setEquipoId1(equipo1);
        rest.setEquipoId2(equipo2);
        rest.setJornada("Jornada "+ jornada);
        rest.setResultado("0-0");
        divisionMapper.insertPartidoDivisionEquipo(rest);
    }
}
