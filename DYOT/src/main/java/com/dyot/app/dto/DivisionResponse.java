package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DivisionResponse {

    @JsonProperty("id")
    private Integer divisionId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("temporada_id")
    private Integer temporadaId;

}
