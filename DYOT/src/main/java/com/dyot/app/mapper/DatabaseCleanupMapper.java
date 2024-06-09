package com.dyot.app.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DatabaseCleanupMapper {
    @Delete("DELETE FROM HISTORIALJUGADOREQUIPO")
    void deleteHistorialJugadorEquipo();

    @Delete("DELETE FROM PARTIDODIVISIONEQUIPO")
    void deletePartidoDivisionEquipo();

    @Delete("DELETE FROM ESTADISTICASEQUIPODIVISION")
    void deleteEstadisticasEquipoDivision();

    @Delete("DELETE FROM EQUIPO")
    void deleteEquipo();

    @Delete("DELETE FROM JUGADOR")
    void deleteJugador();

    @Delete("DELETE FROM DIVISION")
    void deleteDivision();

    @Delete("DELETE FROM TEMPORADA")
    void deleteTemporada();

    @Update("ALTER SEQUENCE HISTORIALJUGADOREQUIPO_SEQ RESTART START WITH 1")
    void resetHistorialJugadorEquipoSeq();

    @Update("ALTER SEQUENCE PARTIDODIVISIONEQUIPO_SEQ RESTART START WITH 1")
    void resetPartidoDivisionEquipoSeq();

    @Update("ALTER SEQUENCE ESTADISTICASQUIPODIVISION_SEQ RESTART START WITH 1")
    void resetEstadisticasEquipoDivisionSeq();

    @Update("ALTER SEQUENCE EQUIPO_SEQ RESTART START WITH 1")
    void resetEquipoSeq();

    @Update("ALTER SEQUENCE JUGADOR_SEQ RESTART START WITH 1")
    void resetJugadorSeq();

    @Update("ALTER SEQUENCE DIVISION_SEQ RESTART START WITH 1")
    void resetDivisionSeq();

    @Update("ALTER SEQUENCE TEMPORADA_SEQ RESTART START WITH 1")
    void resetTemporadaSeq();
}
