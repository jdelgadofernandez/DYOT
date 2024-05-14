package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class EquipoResponse {

    @JsonProperty("id")
    private Integer equipoid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("short_name")
    private String short_Name;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("primary_color")
    private String primaryColor;

    @JsonProperty("secondary_color")
    private String secondaryColor;

    @JsonProperty("ActivePlayers")
    private List<PlayerActiveTeamResponse> playerActiveTeamResponse;
    
}
