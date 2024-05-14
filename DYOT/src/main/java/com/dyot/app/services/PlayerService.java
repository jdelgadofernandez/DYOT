package com.dyot.app.services;

import com.dyot.app.dto.PlayerActiveTeamResponse;
import com.dyot.app.dto.PlayerResponse;

import java.util.List;

public interface PlayerService {

    List<PlayerResponse> findAll();
    PlayerResponse findById(Integer id);
    List<PlayerActiveTeamResponse> findByTeamId(Integer id);


}
