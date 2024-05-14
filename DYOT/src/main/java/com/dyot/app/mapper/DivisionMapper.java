package com.dyot.app.mapper;

import com.dyot.app.entities.DivisionRest;
import com.dyot.app.entities.EquipoRest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DivisionMapper {

	@Select("SELECT * FROM DIVISION")
	List<DivisionRest> findAll();

	@Select("SELECT * FROM DIVISION WHERE DIVISIONDID = #{id}")
	EquipoRest findById(Integer id);

	@Select("SELECT D.* FROM DIVISION D\n" +
			"WHERE TEMPORADAID = #{id}")
	List<DivisionRest> findBySeasonId(Integer id);
}
