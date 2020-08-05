package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ResultDTO;
import doan2020.SportTournamentSupportSystem.entity.ResultEntity;

@Component
public class ResultConverter {
	
	public ResultEntity toEntity(ResultDTO dto) {
		ResultEntity entity = new ResultEntity();
		return entity;
	}
	
	public ResultDTO toDTO(ResultEntity entity) {
		ResultDTO dto = new ResultDTO();
		return dto;
	}
}
