package com.dyot.app.mapper;

import java.util.List;

import com.dyot.app.entities.EquipoResultsRest;
import com.dyot.app.entities.EstadisticasEquipoDivisionRest;
import com.dyot.app.entities.PlayerTeamListRest;
import org.apache.ibatis.annotations.*;

import com.dyot.app.entities.EquipoRest;

@Mapper
public interface EquipoMapper {

	@Select("SELECT * FROM EQUIPO")
	List<EquipoRest> findAll();

	@Select("SELECT * FROM EQUIPO E WHERE E.EQUIPOID = #{id}")
	EquipoRest findById(Integer id);

	@Select("SELECT E.*,EED.PARTIDOS,EED.VICTORIAS,EED.EMPATES,EED.DERROTAS,EED.GOLESFAVOR,EED.GOLESENCONTRA,EED.PUNTOS FROM EQUIPO E\n" +
			"LEFT JOIN estadisticasequipodivision EED ON E.EQUIPOID = EED.EQUIPOID\n" +
			"WHERE EED.DIVISIONID = #{id}"+
			"ORDER BY EED.PUNTOS DESC, EED.GOLESFAVOR DESC")
	List<EquipoResultsRest> findByDivisionId(Integer id);



	@Select("SELECT E.*,HJE.FECHAINICIO,HJE.FECHAFIN FROM JUGADOR J\n" +
			"LEFT JOIN historialjugadorequipo HJE ON HJE.JUGADORID = J.JUGADORID\n" +
			"LEFT JOIN EQUIPO E ON E.EQUIPOID = HJE.EQUIPOID\n" +
			"WHERE J.JUGADORID = #{id} AND HJE.FECHAFIN IS NULL")
	List<PlayerTeamListRest> retrievePlayerHistoryTeamList(Integer id);

	@Update("UPDATE EQUIPO " +
			"SET NAME = #{name}, " +
			"SHORT_NAME = #{short_Name}, " +
			"LOGO = #{logo}, " +
			"PRIMARYCOLOR = #{primaryColor}, " +
			"SECONDARYCOLOR = #{secondaryColor}" +
			"WHERE EQUIPOID=#{equipoid}")
	int updateTeam(EquipoRest equipoRest);

	@Insert("INSERT INTO Equipo (equipoid, name, short_name, logo, primarycolor, secondarycolor) VALUES (EQUIPO_SEQ.NEXTVAL, #{name}, #{short_Name}, #{logo}, #{primaryColor}, #{secondaryColor})")
	@Options(useGeneratedKeys = true, keyProperty = "equipoid", keyColumn = "equipoid")
	int insertTeam(EquipoRest equipoRest);

	@Insert("INSERT INTO ESTADISTICASEQUIPODIVISION (ID, DIVISIONID, EQUIPOID, GOLESFAVOR, GOLESENCONTRA, PUNTOS, PARTIDOS, VICTORIAS, EMPATES, DERROTAS) " +
			"VALUES (ESTADISTICASQUIPODIVISION_SEQ.NEXTVAL, #{divisionId}, #{equipoId}, #{golesFavor}, #{golesEnContra}, #{puntos}, #{partidos}, #{victorias}, #{empates}, #{derrotas})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
	int insertEstadisticasEquipoDivision(EstadisticasEquipoDivisionRest estadisticasEquipoDivisionRest);

	@Select("SELECT equipoid FROM Equipo ORDER BY equipoid DESC FETCH FIRST 1 ROWS ONLY")
	int getLastInsertedId();
}
