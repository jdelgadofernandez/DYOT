package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MovementResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("equipoid")
    private Integer equipoid;

    @JsonProperty("teamName")
    private String teamName;

    @JsonProperty("teamLogo")
    private String teamLogo;

    @JsonProperty("jugadorid")
    private Integer jugadorid;

    @JsonProperty("playerName")
    private String playerName;

    @JsonProperty("fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    @JsonProperty("tipo")
    private String tipo;

}
