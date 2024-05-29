package com.dyot.app.services.impl;

import com.dyot.app.dto.EquipoResponse;
import com.dyot.app.dto.MovementResponse;
import com.dyot.app.entities.MovementRest;
import com.dyot.app.mapper.MovementMapper;
import com.dyot.app.services.EquipoService;
import com.dyot.app.services.MovementService;
import com.dyot.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementMapper movementMapper;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private PlayerService playerService;

    @Override
    public List<MovementResponse> retrieveLastTen() {
        List<MovementRest> movementRestList = movementMapper.retrieveLastTen();
        List<MovementResponse> movementResponses = new ArrayList<>();

        for(MovementRest movementRest: movementRestList){
            MovementResponse movementResponse = this.restToResponse(movementRest);
            movementResponses.add(movementResponse);
        }

        return movementResponses;
    }

    @Override
    public List<MovementResponse> retrieveByTeamId(Integer equipoid) {
        List<MovementRest> movementRestList = movementMapper.retrieveByTeamId(equipoid);
        List<MovementResponse> movementResponses = new ArrayList<>();

        for(MovementRest movementRest: movementRestList){
            MovementResponse movementResponse = this.restToResponse(movementRest);
            movementResponses.add(movementResponse);
        }

        return movementResponses;
    }

    @Override
    public List<MovementResponse> retrieveByPlayerId(Integer jugadorid) {
        List<MovementRest> movementRestList = movementMapper.retrieveByPlayerId(jugadorid);
        List<MovementResponse> movementResponses = new ArrayList<>();

        for(MovementRest movementRest: movementRestList){
            MovementResponse movementResponse = this.restToResponse(movementRest);
            movementResponses.add(movementResponse);
        }

        return movementResponses;
    }

    private MovementResponse restToResponse(MovementRest movementRest){
        MovementResponse response = new MovementResponse();
        EquipoResponse equipoResponse = equipoService.findById(movementRest.getEquipoid());
        response.setId(movementRest.getId());
        response.setEquipoid(movementRest.getEquipoid());
        response.setTeamName(equipoResponse.getName());
        response.setTeamLogo(equipoResponse.getLogo());
        response.setJugadorid(movementRest.getJugadorid());
        response.setPlayerName(playerService.findById(movementRest.getJugadorid()).getIngameName());
        response.setFecha(movementRest.getFecha());
        response.setTipo(movementRest.getTipo());
        return response;
    }
}
