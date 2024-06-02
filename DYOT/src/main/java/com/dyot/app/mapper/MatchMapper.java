package com.dyot.app.mapper;


import com.dyot.app.entities.MatchRest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("UPDATE PartidoDivisionEquipo\n" +
            "SET RESULTADO = #{resultado}\n" +
            "WHERE PARTIDOID = #{id}")
    int updateResult(Integer id, String resultado);

    @Update("UPDATE EstadisticasEquipoDivision\n" +
            "SET GOLESFAVOR = GOLESFAVOR + #{golesFavor},\n" +
            "GOLESENCONTRA = GOLESENCONTRA + #{golesEnContra},\n" +
            "PUNTOS = PUNTOS + #{puntos},\n" +
            "PARTIDOS = PARTIDOS + 1,\n" +
            "VICTORIAS = VICTORIAS + #{victorias},\n" +
            "EMPATES = EMPATES + #{empates},\n" +
            "DERROTAS = DERROTAS + #{derrotas}\n" +
            "WHERE EQUIPOID = #{equipoId} AND DIVISIONID = #{divisionId}")
    void updateTeamStats(Integer equipoId, Integer divisionId,
                         int golesFavor, int golesEnContra,
                         int puntos, int victorias,
                         int empates, int derrotas);
}
