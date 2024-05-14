package com.dyot.app.dto;

import com.dyot.app.entities.EquipoRest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EquipoResultsResponse extends EquipoResponse {

    @JsonProperty("partidos")
    private Integer partidos;

    @JsonProperty("victorias")
    private Integer victorias;

    @JsonProperty("empates")
    private Integer empates;

    @JsonProperty("derrotas")
    private Integer derrotas;

    @JsonProperty("golesfavor")
    private Integer golesfavor;

    @JsonProperty("golesencontra")
    private Integer golesencontra;

    @JsonProperty("puntos")
    private Integer puntos;

}
