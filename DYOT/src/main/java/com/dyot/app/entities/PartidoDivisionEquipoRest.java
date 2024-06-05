package com.dyot.app.entities;

import lombok.Data;

import java.util.Date;

@Data
public class PartidoDivisionEquipoRest {
    private Integer partidoid;
    private Integer divisionId;
    private Integer equipoId1;
    private Integer equipoId2;
    private Date fechaPartido;
    private String jornada;
    private String resultado;

}
