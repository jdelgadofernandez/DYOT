package com.dyot.app.entities;

import lombok.Data;

@Data
public class EquipoResultsRest extends EquipoRest{

    private Integer partidos;
    private Integer victorias;
    private Integer empates;
    private Integer derrotas;
    private Integer golesfavor;
    private Integer golesencontra;
    private Integer puntos;

}
