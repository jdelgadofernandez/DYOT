package com.dyot.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerStoryResponse extends PlayerResponse{
    @JsonProperty("TeamList")
    private PlayerTeamListResponse playerTeamListResponse;

}
