package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class PlayerActiveTeamResponse extends PlayerResponse{

    @JsonProperty("fecha_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date fecha_inicio;

    @JsonProperty("equipoid")
    Integer equipoid;
}
