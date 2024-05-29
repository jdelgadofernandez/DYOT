package com.dyot.app.services;

import com.dyot.app.dto.MovementResponse;
import com.dyot.app.entities.MovementRest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovementService {

    List<MovementResponse> retrieveLastTen();

    List<MovementResponse> retrieveByTeamId(Integer equipoid);

    List<MovementResponse> retrieveByPlayerId(Integer jugadorid);

}
