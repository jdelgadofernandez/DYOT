package com.dyot.app.mapper;

import com.dyot.app.entities.EquipoRest;
import com.dyot.app.entities.SeasonRest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeasonMapper {

	@Select("SELECT * FROM TEMPORADA")
	List<SeasonRest> findAll();

	@Select("SELECT * FROM EquipoMapper WHERE TEMPORADADID = #{id}")
	SeasonRest findById(Integer id);
}
