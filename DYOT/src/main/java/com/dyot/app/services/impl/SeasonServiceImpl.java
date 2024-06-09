package com.dyot.app.services.impl;

import com.dyot.app.dto.SeasonResponse;
import com.dyot.app.entities.SeasonRest;
import com.dyot.app.mapper.*;
import com.dyot.app.services.DivisionService;
import com.dyot.app.services.EquipoService;
import com.dyot.app.services.PlayerService;
import com.dyot.app.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonMapper seasonMapper;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private DatabaseCleanupMapper databaseCleanupMapper;

    @Override
    public int insertSeason(SeasonResponse seasonResponse, int division, int equipos) {
        SeasonRest rest = new SeasonRest();

        rest.setName(seasonResponse.getName());
        rest.setYear(seasonResponse.getYear());
        int answer = seasonMapper.insertSeason(rest);
        int seasonId = seasonMapper.getLastInsertedId();
        divisionService.createFromSeason(seasonId,division,equipos);


        return answer;
    }

    @Override
    public void cleanDatabase() {
        databaseCleanupMapper.deleteHistorialJugadorEquipo();
        databaseCleanupMapper.deletePartidoDivisionEquipo();
        databaseCleanupMapper.deleteEstadisticasEquipoDivision();
        databaseCleanupMapper.deleteEquipo();
        databaseCleanupMapper.deleteJugador();
        databaseCleanupMapper.deleteDivision();
        databaseCleanupMapper.deleteTemporada();

        databaseCleanupMapper.resetHistorialJugadorEquipoSeq();
        databaseCleanupMapper.resetPartidoDivisionEquipoSeq();
        databaseCleanupMapper.resetEstadisticasEquipoDivisionSeq();
        databaseCleanupMapper.resetEquipoSeq();
        databaseCleanupMapper.resetJugadorSeq();
        databaseCleanupMapper.resetDivisionSeq();
        databaseCleanupMapper.resetTemporadaSeq();
    }
}
