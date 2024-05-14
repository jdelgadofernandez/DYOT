package com.dyot.app.mapper;

import com.dyot.app.dto.PlayerActiveTeamResponse;
import com.dyot.app.entities.EquipoRest;
import com.dyot.app.entities.PlayerActiveTeamRest;
import com.dyot.app.entities.PlayerRest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlayerMapper {

    @Select("SELECT * FROM JUGADOR")
    List<PlayerRest> findAll();

    @Select("SELECT * FROM JUGADOR WHERE JUGADORID=#{id}")
    PlayerRest findById(Integer id);

    @Select("SELECT J.* FROM JUGADOR J\n" +
            "LEFT JOIN historialjugadorequipo HJE ON HJE.JUGADORID = J.JUGADORID\n" +
            "LEFT JOIN EQUIPO E ON E.EQUIPOID = HJE.EQUIPOID\n" +
            "WHERE E.EQUIPOID = #{id} AND HJE.FECHAFIN IS NOT NULL")
    List<PlayerActiveTeamRest> findByTeamId(Integer id);
}
