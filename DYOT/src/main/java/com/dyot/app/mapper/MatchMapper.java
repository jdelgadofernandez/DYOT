package com.dyot.app.mapper;


import com.dyot.app.entities.MatchRest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MatchMapper {

    @Select("SELECT PDE.* FROM partidodivisionequipo PDE\n" +
            "WHERE pde.partidoid = #{id}")
    MatchRest retrieveMatchById(Integer id);

    @Select("SELECT PDE.* FROM partidodivisionequipo PDE\n" +
            "LEFT JOIN DIVISION D ON d.divisionid = pde.divisionid\n" +
            "WHERE pde.divisionid = #{id}")
    List<MatchRest> matchListByDivisionId(Integer id);


    @Select("SELECT PDE.* FROM partidodivisionequipo PDE\n" +
            "ORDER BY pde.fechapartido ASC \n" +
            "FETCH FIRST 10 ROWS ONLY")
    List<MatchRest> matchNextFourMatches();
}
