package com.dyot.app.services;


import com.dyot.app.dto.MatchResponse;

import java.util.List;

public interface MatchService {

    public MatchResponse retrieveMatchById(Integer id, String name);

    public List<MatchResponse> matchListByDivisionId(Integer id, String name);

    public List<MatchResponse> matchNextFourMatches(String name);

    public int updateMatch(Integer id, String resultado);

}
