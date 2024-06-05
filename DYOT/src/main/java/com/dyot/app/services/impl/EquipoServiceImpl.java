package com.dyot.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.dyot.app.dto.EquipoResultsResponse;
import com.dyot.app.dto.PlayerActiveTeamResponse;
import com.dyot.app.entities.EquipoResultsRest;
import com.dyot.app.entities.EstadisticasEquipoDivisionRest;
import com.dyot.app.services.MovementService;
import com.dyot.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyot.app.dto.EquipoResponse;
import com.dyot.app.entities.EquipoRest;
import com.dyot.app.mapper.EquipoMapper;
import com.dyot.app.services.EquipoService;

@Service
public class EquipoServiceImpl  implements EquipoService{

	@Autowired
	private EquipoMapper equipoMapper;

	@Autowired
	private PlayerService playerService;


	@Override
	public List<EquipoResponse> findAll() {
		List<EquipoRest> equipoRestList = equipoMapper.findAll();
		List<EquipoResponse> equipoResponseList = new ArrayList<>();
		
		for(int i = 0;i<equipoRestList.size();i++) {
			equipoRestToRespose(equipoRestList, i, equipoResponseList);
			List<PlayerActiveTeamResponse> playerActiveTeamResponseList = playerService.findByTeamId(equipoRestList.get(i).getEquipoid());
			equipoResponseList.get(i).setPlayerActiveTeamResponse(playerActiveTeamResponseList);
		}
		
		return equipoResponseList;
	}

	@Override
	public EquipoResponse findById(Integer id) {
		EquipoRest equipoRest = equipoMapper.findById(id);
		EquipoResultsResponse response = new EquipoResultsResponse();
		response.setEquipoid(equipoRest.getEquipoid());
		response.setName(equipoRest.getName());
		response.setShort_Name(equipoRest.getShort_Name());
		response.setLogo(equipoRest.getLogo()); // Asumiendo que logo ya está en base64
		response.setPrimaryColor(equipoRest.getPrimaryColor());
		response.setSecondaryColor(equipoRest.getSecondaryColor());
		List<PlayerActiveTeamResponse> playerActiveTeamResponseList = playerService.findByTeamId(equipoRest.getEquipoid());
		response.setPlayerActiveTeamResponse(playerActiveTeamResponseList);
		return response;
	}

	@Override
	public List<EquipoResultsResponse> findByDivisionId(Integer id) {
		List<EquipoResultsRest> equipoResultsRests = equipoMapper.findByDivisionId(id);
		List<EquipoResultsResponse> equipoResultsResponses = new ArrayList<>();
		for(int i = 0;i<equipoResultsRests.size();i++) {
			equipoResultRestToResponse(equipoResultsRests, i, equipoResultsResponses);

			List<PlayerActiveTeamResponse> playerActiveTeamResponseList = playerService.findByTeamId(equipoResultsRests.get(i).getEquipoid());
			equipoResultsResponses.get(i).setPlayerActiveTeamResponse(playerActiveTeamResponseList);
		}


		return equipoResultsResponses;
	}

	@Override
	public int updateTeam(EquipoResponse equipoResponse) {
		EquipoRest rest = this.responseToRest(equipoResponse);

		return equipoMapper.updateTeam(rest);
	}

	@Override
	public void createFromSeason(int equipos,Integer divId, Integer seasonId){
	for(int i = 0;i<equipos;i++){
		EquipoRest rest = new EquipoRest();
		rest.setName("Equipo"+(i+1));
		rest.setShort_Name("EQ"+(i+1));
		rest.setLogo("assets/images/default.png");
		rest.setPrimaryColor("#000000");
		rest.setSecondaryColor("#FFFFFF");
		equipoMapper.insertTeam(rest);

		Integer equipoId = equipoMapper.getLastInsertedId();

		EstadisticasEquipoDivisionRest estadisticasEquipoDivisionRest = getEstadisticasEquipoDivisionRest(divId, equipoId);
		equipoMapper.insertEstadisticasEquipoDivision(estadisticasEquipoDivisionRest);
		playerService.insertFromSeason(divId,equipoId,seasonId);
	}
	}

	private static EstadisticasEquipoDivisionRest getEstadisticasEquipoDivisionRest(Integer divId, Integer equipoId) {
		EstadisticasEquipoDivisionRest estadisticasEquipoDivisionRest = new EstadisticasEquipoDivisionRest();
		estadisticasEquipoDivisionRest.setDivisionId(divId);
		estadisticasEquipoDivisionRest.setEquipoId(equipoId);
		estadisticasEquipoDivisionRest.setGolesFavor(0);
		estadisticasEquipoDivisionRest.setGolesEnContra(0);
		estadisticasEquipoDivisionRest.setPuntos(0);
		estadisticasEquipoDivisionRest.setPartidos(0);
		estadisticasEquipoDivisionRest.setVictorias(0);
		estadisticasEquipoDivisionRest.setEmpates(0);
		estadisticasEquipoDivisionRest.setDerrotas(0);
		return estadisticasEquipoDivisionRest;
	}

	private EquipoRest responseToRest(EquipoResponse equipoResponse){
		EquipoRest rest = new EquipoRest();
		rest.setEquipoid(equipoResponse.getEquipoid());
		rest.setName(equipoResponse.getName());
		rest.setShort_Name(equipoResponse.getShort_Name());
		rest.setLogo(equipoResponse.getLogo());
		rest.setPrimaryColor(equipoResponse.getPrimaryColor());
		rest.setSecondaryColor(equipoResponse.getSecondaryColor());

		return rest;
	}

	private static void equipoResultRestToResponse(List<EquipoResultsRest> equipoResultsRests, int i, List<EquipoResultsResponse> equipoResultsResponses) {
		EquipoResultsResponse response = new EquipoResultsResponse();
		response.setEquipoid(equipoResultsRests.get(i).getEquipoid());
		response.setName(equipoResultsRests.get(i).getName());
		response.setShort_Name(equipoResultsRests.get(i).getShort_Name());
		response.setLogo(equipoResultsRests.get(i).getLogo()); // Asumiendo que logo ya está en base64
		response.setPrimaryColor(equipoResultsRests.get(i).getPrimaryColor());
		response.setSecondaryColor(equipoResultsRests.get(i).getSecondaryColor());

		response.setPartidos(equipoResultsRests.get(i).getPartidos());
		response.setVictorias(equipoResultsRests.get(i).getVictorias());
		response.setEmpates(equipoResultsRests.get(i).getEmpates());
		response.setDerrotas(equipoResultsRests.get(i).getDerrotas());
		response.setGolesfavor(equipoResultsRests.get(i).getGolesfavor());
		response.setGolesencontra(equipoResultsRests.get(i).getGolesencontra());
		response.setPuntos(equipoResultsRests.get(i).getPuntos());

		equipoResultsResponses.add(response);
	}

	private static void equipoRestToRespose(List<EquipoRest> equipoRestList, int i, List<EquipoResponse> equipoResponseList) {
		EquipoResponse equipoResponse = new EquipoResponse();

		equipoResponse.setEquipoid(equipoRestList.get(i).getEquipoid());
		equipoResponse.setName(equipoRestList.get(i).getName());
		equipoResponse.setLogo(equipoRestList.get(i).getLogo());
		equipoResponse.setShort_Name(equipoRestList.get(i).getShort_Name());
		equipoResponse.setPrimaryColor(equipoRestList.get(i).getPrimaryColor());
		equipoResponse.setSecondaryColor(equipoRestList.get(i).getSecondaryColor());
		equipoResponseList.add(equipoResponse);
	}


}
