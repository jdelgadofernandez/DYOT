package com.dyot.app.entities;

import lombok.Data;

import java.util.Date;

@Data
public class MatchRest {

    private Integer partidoId;

    private Integer divisionId;

    private Integer equipoid1;

    private Integer equipoid2;

    private Date fechapartido;

    private String jornada;

    private String resultado;

}
