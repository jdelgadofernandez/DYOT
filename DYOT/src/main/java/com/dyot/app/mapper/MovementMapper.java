package com.dyot.app.mapper;

import com.dyot.app.entities.MovementRest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovementMapper {

    @Select("SELECT *\n" +
            "FROM\n" +
            "(SELECT hje.equipoid,hje.id, hje.jugadorid, hje.fechainicio AS FECHA,'ENTRADA' AS TIPO\n" +
            "FROM HISTORIALJUGADOREQUIPO HJE\n" +
            "UNION ALL\n" +
            "SELECT hje.equipoid,hje.id, hje.jugadorid,hje.fechafin AS FECHA, 'SALIDA' AS TIPO\n" +
            "FROM historialjugadorequipo HJE\n" +
            "WHERE hje.fechafin IS NOT NULL)\n" +
            "ORDER BY FECHA DESC\n" +
            "FETCH FIRST 10 ROWS ONLY")
    List<MovementRest> retrieveLastTen();

    @Select("SELECT *\n" +
            "FROM\n" +
            "(SELECT hje.equipoid as equipoid,hje.id, hje.jugadorid as jugadorid, hje.fechainicio AS FECHA,'ENTRADA' AS TIPO\n" +
            "FROM HISTORIALJUGADOREQUIPO HJE\n" +
            "UNION ALL\n" +
            "SELECT hje.equipoid as equipoid,hje.id, hje.jugadorid as jugadorid ,hje.fechafin AS FECHA, 'SALIDA' AS TIPO\n" +
            "FROM historialjugadorequipo HJE\n" +
            "WHERE hje.fechafin IS NOT NULL)\n" +
            "WHERE equipoid = #{equipoid}\n" +
            "ORDER BY FECHA DESC\n" +
            "FETCH FIRST 10 ROWS ONLY")
    List<MovementRest> retrieveByTeamId(Integer equipoid);

    @Select("SELECT *\n" +
            "FROM\n" +
            "(SELECT hje.equipoid as equipoid,hje.id, hje.jugadorid as jugadorid, hje.fechainicio AS FECHA,'ENTRADA' AS TIPO\n" +
            "FROM HISTORIALJUGADOREQUIPO HJE\n" +
            "UNION ALL\n" +
            "SELECT hje.equipoid as equipoid,hje.id, hje.jugadorid as jugadorid ,hje.fechafin AS FECHA, 'SALIDA' AS TIPO\n" +
            "FROM historialjugadorequipo HJE\n" +
            "WHERE hje.fechafin IS NOT NULL)\n" +
            "WHERE jugadorid = #{jugadorid}\n" +
            "ORDER BY FECHA DESC")
    List<MovementRest> retrieveByPlayerId(Integer jugadorid);

    @Select("SELECT HJE.* FROM historialjugadorequipo HJE\n" +
            "WHERE HJE.JUGADORID = #{jugadorid}")
    List<MovementRest> retrieveTeamsByPlayerId(Integer jugadorid);
}
