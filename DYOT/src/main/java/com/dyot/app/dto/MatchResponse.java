package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MatchResponse {

    @JsonProperty("partidoId")
    private Integer partidoid;

    @JsonProperty("divisionId")
    private Integer divisionid;

    @JsonProperty("divisionName")
    private String divisionName;

    @JsonProperty("equipo1")
    private EquipoResponse equipo1;

    @JsonProperty("equipo2")
    private EquipoResponse equipo2;

    @JsonProperty("fechapartido")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date fechapartido;

    @JsonProperty("jornada")
    private String jornada;

    @JsonProperty("resultado")
    private String resultado;

}
