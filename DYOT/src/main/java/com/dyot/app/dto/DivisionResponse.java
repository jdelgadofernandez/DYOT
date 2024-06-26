package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DivisionResponse {

    @JsonProperty("divisionId")
    private Integer divisionId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("temporada_id")
    private Integer temporadaId;

    @JsonProperty("equipos")
    private List<EquipoResultsResponse> teams;

    @JsonProperty("matches")
    private List<MatchResponse> matches;

}
