package com.dyot.app.mapper;

import java.util.List;

import com.dyot.app.entities.EquipoResultsRest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.dyot.app.entities.EquipoRest;

@Mapper
public interface EquipoMapper {

	@Select("SELECT * FROM EQUIPO")
	List<EquipoRest> findAll();

	@Select("SELECT * FROM EQUIPO WHERE EQUIPODID = #{id}")
	EquipoRest findById(Integer id);

	@Select("SELECT E.*,EED.PARTIDOS,EED.VICTORIAS,EED.EMPATES,EED.DERROTAS,EED.GOLESFAVOR,EED.GOLESENCONTRA,EED.PUNTOS FROM EQUIPO E\n" +
			"LEFT JOIN estadisticasequipodivision EED ON E.EQUIPOID = EED.EQUIPOID\n" +
			"WHERE EED.DIVISIONID = #{id}")
	List<EquipoResultsRest> findByDivisionId(Integer id);
}
