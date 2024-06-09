package com.dyot.app.services.impl;


import com.dyot.app.dto.PlayerActiveTeamResponse;
import com.dyot.app.dto.PlayerResponse;
import com.dyot.app.dto.PlayerStoryResponse;
import com.dyot.app.entities.*;
import com.dyot.app.mapper.DivisionMapper;
import com.dyot.app.mapper.EquipoMapper;
import com.dyot.app.mapper.PlayerMapper;
import com.dyot.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private DivisionMapper divisionMapper;

    @Override
    public List<PlayerResponse> findAll() {
        return playerMapper.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerResponse findById(Integer id) {
        PlayerRest player = playerMapper.findById(id);
        return convertToResponse(player);
    }

    @Override
    public List<PlayerActiveTeamResponse> findByTeamId(Integer id) {
        return playerMapper.findByTeamId(id).stream()
                .map(this::convertToResponseActive)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerStoryResponse retrievePlayerStory(Integer id) {
        PlayerRest player = playerMapper.findById(id);
        PlayerStoryResponse response = convertPlayerStoryToResponse(player);
        //response.setPlayerTeamListResponse(equipoMapper.retrievePlayerHistoryTeamList(id));

        return null;
    }

    @Override
    public int updatePlayer(PlayerActiveTeamResponse playerResponse) {
        PlayerRest playerRest = this.responseToRest(playerResponse);

        if(playerResponse.getEquipoid()==null){
            playerMapper.updateTeamPlayer(playerResponse.getJugadorid());

        }
        else{
        HistorialJugadorEquipoRest actualTeam = playerMapper.findIfPlayerHaveATeam(playerResponse.getJugadorid());

        if(actualTeam != null){
            playerMapper.updateTeamPlayer(playerResponse.getJugadorid());
        }

        DivTempRest divTempRest = divisionMapper.findDivIdFromTeamId(playerResponse.getEquipoid());
        HistorialJugadorEquipoRest historialJugadorEquipoRest = new HistorialJugadorEquipoRest();
        historialJugadorEquipoRest.setEquipoId(playerResponse.getEquipoid());
        historialJugadorEquipoRest.setJugadorId(playerResponse.getJugadorid());
        historialJugadorEquipoRest.setDivisionId(divTempRest.getDivisionId());
        historialJugadorEquipoRest.setTemporadaId(divTempRest.getTemporadaId());
        playerMapper.insertNewHistorialJugadorEquipo(historialJugadorEquipoRest);
        }
        return playerMapper.updatePlayer(playerRest);
    }

    @Override
    public void insertFromSeason(Integer divId,Integer equipoId,Integer seasonId) {
        for(int i = 0;i<3;i++){
            PlayerRest playerRest = new PlayerRest();
            playerRest.setName("Nombre");
            playerRest.setSurname("Apellido");
            playerRest.setIngameName("Jugador"+(i+1));
            playerMapper.insertPlayer(playerRest);

            Integer playerId = playerMapper.getLastInsertedId();

            HistorialJugadorEquipoRest historialJugadorEquipoRest = new HistorialJugadorEquipoRest();
            historialJugadorEquipoRest.setDivisionId(divId);
            historialJugadorEquipoRest.setEquipoId(equipoId);
            historialJugadorEquipoRest.setJugadorId(playerId);
            historialJugadorEquipoRest.setTemporadaId(seasonId);
            playerMapper.insertHistorialJugadorEquipo(historialJugadorEquipoRest);
        }


    }

    private PlayerRest responseToRest(PlayerActiveTeamResponse playerResponse){
        PlayerRest rest = new PlayerRest();
        rest.setJugadorid(playerResponse.getJugadorid());
        rest.setName(playerResponse.getName());
        rest.setSurname(playerResponse.getSurname());
        rest.setIngameName(playerResponse.getIngameName());

        return rest;
    }


    private PlayerResponse convertToResponse(PlayerRest playerRest) {
        PlayerResponse response = new PlayerResponse();
        response.setJugadorid(playerRest.getJugadorid());
        response.setName(playerRest.getName());
        response.setSurname(playerRest.getSurname());
        response.setIngameName(playerRest.getIngameName());
        return response;
    }

    private PlayerStoryResponse convertPlayerStoryToResponse(PlayerRest playerRest) {
        PlayerStoryResponse response = new PlayerStoryResponse();
        response.setJugadorid(playerRest.getJugadorid());
        response.setName(playerRest.getName());
        response.setSurname(playerRest.getSurname());
        response.setIngameName(playerRest.getIngameName());
        return response;
    }


    private PlayerActiveTeamResponse convertToResponseActive(PlayerActiveTeamRest playerRest) {
        PlayerActiveTeamResponse response = new PlayerActiveTeamResponse();
        response.setJugadorid(playerRest.getJugadorid());
        response.setName(playerRest.getName());
        response.setSurname(playerRest.getSurname());
        response.setIngameName(playerRest.getIngameName());
        response.setFecha_inicio(playerRest.getFechaInicio());
        return response;
    }
}
