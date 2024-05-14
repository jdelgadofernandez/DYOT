package com.dyot.app.services.impl;


import com.dyot.app.dto.PlayerActiveTeamResponse;
import com.dyot.app.dto.PlayerResponse;
import com.dyot.app.entities.PlayerActiveTeamRest;
import com.dyot.app.entities.PlayerRest;
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

    private PlayerResponse convertToResponse(PlayerRest playerRest) {
        PlayerResponse response = new PlayerResponse();
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
        response.setFechaInicio(playerRest.getFechaInicio());
        return response;
    }
}
