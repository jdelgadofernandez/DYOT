package com.dyot.app.mapper;

import com.dyot.app.entities.DivisionRest;
import com.dyot.app.entities.EquipoRest;
import com.dyot.app.entities.PartidoDivisionEquipoRest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DivisionMapper {

	@Select("SELECT * FROM DIVISION")
	List<DivisionRest> findAll();

	@Select("SELECT * FROM DIVISION WHERE DIVISIONDID = #{id}")
	DivisionRest findById(Integer id);

	@Select("SELECT D.* FROM DIVISION D\n" +
			"WHERE TEMPORADAID = #{id}")
	List<DivisionRest> findBySeasonId(Integer id);

	@Insert("INSERT INTO Division (divisionid, name, temporadaid) VALUES (DIVISION_SEQ.NEXTVAL,#{name}, #{temporadaId})")
	@Options(useGeneratedKeys = true, keyProperty = "divisionId", keyColumn = "divisionId")
	int createDivision(DivisionRest divisionRest);

	@Select("SELECT DIVISIONID FROM DIVISION ORDER BY DIVISIONID DESC FETCH FIRST 1 ROWS ONLY")
	int getLastInsertedId();

	@Select("SELECT E.EQUIPOID FROM EQUIPO E\n" +
			"LEFT JOIN estadisticasequipodivision EED ON E.EQUIPOID = EED.EQUIPOID\n" +
			"WHERE EED.DIVISIONID = #{id}"+
			"ORDER BY EED.PUNTOS DESC, EED.GOLESFAVOR DESC")
	List<Integer> findTeamIdListByDivisionId(Integer id);

	@Insert("INSERT INTO PartidoDivisionEquipo (partidoid, divisionid, equipoid1, equipoid2, fechapartido, jornada, resultado) " +
			"VALUES (PARTIDODIVISIONEQUIPO_SEQ.NEXTVAL, #{divisionId}, #{equipoId1}, #{equipoId2}, CURRENT_TIMESTAMP, #{jornada}, #{resultado})")
	@Options(useGeneratedKeys = true, keyProperty = "partidoid", keyColumn = "partidoid")
	int insertPartidoDivisionEquipo(PartidoDivisionEquipoRest partidoDivisionEquipo);

}
