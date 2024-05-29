package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerResponse {

    @JsonProperty("jugadorid")
    private Integer jugadorid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("ingame_name")
    private String ingameName;

}
