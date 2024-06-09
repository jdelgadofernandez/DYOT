package com.dyot.app.mapper;

import com.dyot.app.entities.EquipoRest;
import com.dyot.app.entities.SeasonRest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeasonMapper {


	@Select("SELECT * FROM TEMPORADA")
	List<SeasonRest> findAll();

	@Select("SELECT * FROM EquipoMapper WHERE TEMPORADADID = #{id}")
	SeasonRest findById(Integer id);


	@Insert("INSERT INTO TEMPORADA (TEMPORADAID,NAME, YEAR) VALUES (TEMPORADA_SEQ.NEXTVAL,#{name}, #{year})")
	@Options(useGeneratedKeys = true, keyProperty = "temporadaId", keyColumn = "TEMPORADAID")
	int insertSeason(SeasonRest seasonRest);

	@Select("SELECT TEMPORADAID FROM TEMPORADA ORDER BY TEMPORADAID DESC FETCH FIRST 1 ROWS ONLY")
	int getLastInsertedId();


}
