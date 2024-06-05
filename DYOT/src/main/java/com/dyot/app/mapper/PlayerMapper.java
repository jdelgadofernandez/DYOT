package com.dyot.app.mapper;

import com.dyot.app.entities.HistorialJugadorEquipoRest;
import com.dyot.app.entities.PlayerActiveTeamRest;
import com.dyot.app.entities.PlayerRest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerMapper {

    @Select("SELECT * FROM JUGADOR")
    List<PlayerRest> findAll();

    @Select("SELECT * FROM JUGADOR WHERE JUGADORID=#{id}")
    PlayerRest findById(Integer id);

    @Select("SELECT J.*, HJE.FECHAINICIO FROM JUGADOR J\n" +
            "LEFT JOIN historialjugadorequipo HJE ON HJE.JUGADORID = J.JUGADORID\n" +
            "LEFT JOIN EQUIPO E ON E.EQUIPOID = HJE.EQUIPOID\n" +
            "WHERE E.EQUIPOID = #{id} AND HJE.FECHAFIN IS NULL")
    List<PlayerActiveTeamRest> findByTeamId(Integer id);

    @Update("UPDATE JUGADOR " +
            "SET NAME = #{name}, " +
            "SURNAME = #{surname}, " +
            "INGAMENAME = #{ingameName} " +
            "WHERE JUGADORID = #{jugadorid}")
    int updatePlayer(PlayerRest playerRest);

    @Insert("INSERT INTO Jugador (jugadorid, name, surname, ingamename) VALUES (JUGADOR_SEQ.NEXTVAL, #{name}, #{surname}, #{ingameName})")
    @Options(useGeneratedKeys = true, keyProperty = "jugadorid", keyColumn = "jugadorid")
    int insertPlayer(PlayerRest playerRest);

    @Select("SELECT jugadorid FROM Jugador ORDER BY jugadorid DESC FETCH FIRST 1 ROWS ONLY")
    int getLastInsertedId();

    @Insert("INSERT INTO HistorialJugadorEquipo (id, divisionid, equipoid, jugadorid, temporadaid, fechainicio, fechafin) " +
            "VALUES (HISTORIALJUGADOREQUIPO_SEQ.NEXTVAL, #{divisionId}, #{equipoId}, #{jugadorId}, #{temporadaId}, CURRENT_TIMESTAMP, #{fechaFin,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    int insertHistorialJugadorEquipo(HistorialJugadorEquipoRest historialJugadorEquipo);

}
