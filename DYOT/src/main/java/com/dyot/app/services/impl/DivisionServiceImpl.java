package com.dyot.app.services.impl;

import com.dyot.app.dto.DivisionResponse;
import com.dyot.app.entities.DivisionRest;
import com.dyot.app.mapper.DivisionMapper;
import com.dyot.app.services.DivisionService;
import com.dyot.app.services.EquipoService;
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

           divisionResponseList.add(response);
        }

        return divisionResponseList;
    }
}
