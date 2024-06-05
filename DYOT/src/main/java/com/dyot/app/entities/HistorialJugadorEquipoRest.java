package com.dyot.app.entities;

import lombok.Data;

import java.util.Date;

@Data
public class HistorialJugadorEquipoRest {
    private Integer id;
    private Integer divisionId;
    private Integer equipoId;
    private Integer jugadorId;
    private Integer temporadaId;
    private Date fechaInicio;
    private Date fechaFin;

}
