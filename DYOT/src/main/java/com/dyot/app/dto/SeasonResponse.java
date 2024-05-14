package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SeasonResponse {

    @JsonProperty("id")
    private Integer temporadaId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("year")
    private Integer year;

}
