package com.dyot.app.entities;

import lombok.Data;

@Data
public class EstadisticasEquipoDivisionRest {
    private Integer id;
    private Integer divisionId;
    private Integer equipoId;
    private int golesFavor;
    private int golesEnContra;
    private int puntos;
    private int partidos;
    private int victorias;
    private int empates;
    private int derrotas;

}
