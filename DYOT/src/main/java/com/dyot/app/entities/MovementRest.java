package com.dyot.app.entities;

import lombok.Data;

import java.util.Date;

@Data
public class MovementRest {

    private Integer id;

    private Integer equipoid;

    private Integer jugadorid;

    private Date fecha;

    private String tipo;
}
