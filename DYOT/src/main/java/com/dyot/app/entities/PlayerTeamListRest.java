package com.dyot.app.entities;

import lombok.Data;

import java.util.Date;

@Data
public class PlayerTeamListRest extends EquipoRest{
    private Date fechaInicio;
    private Date fechaFin;

}
