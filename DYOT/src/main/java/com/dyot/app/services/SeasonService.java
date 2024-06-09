package com.dyot.app.services;

import com.dyot.app.dto.SeasonResponse;

public interface SeasonService {

    int insertSeason(SeasonResponse seasonResponse,int division, int equipos);

    void cleanDatabase();

}
