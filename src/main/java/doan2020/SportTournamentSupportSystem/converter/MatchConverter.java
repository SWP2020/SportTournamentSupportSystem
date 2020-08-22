package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.MatchDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class MatchConverter{
	
	@Autowired
	private ICompetitionService competitionService;
	
	@Autowired
	private IMatchService matchService;
	
	@Autowired
	private Validator validator;
	
	public MatchEntity toEntity(MatchDTO dto){
		System.out.println("MatchConverter: toEntity: start");
		MatchEntity entity = new MatchEntity();
		try {
			entity.setName(dto.getName());
			entity.setNumOfSet(dto.getNumOfSet());
			
			Date expectedDate = validator.formatStringToDate(dto.getExpectedDate());
			entity.setExpectedDate(expectedDate);
			
			entity.setExpectedPlace(dto.getExpectedPlace());
			
			Date realDate = validator.formatStringToDate(dto.getRealDate());
			entity.setRealDate(realDate);
			
			entity.setRealPlace(dto.getRealPlace());
			
			if (dto.getCompetitionId() != null) {
				Long competitionId = dto.getCompetitionId();
				CompetitionEntity competition = competitionService.findOneById(competitionId);
				entity.setCompetition(competition);
			}
			
			if (dto.getNextMatchId() != null) {
				Long nextMatchId = dto.getNextMatchId();
				MatchEntity nextMatch = matchService.findOneById(nextMatchId);
				entity.setNextMatch(nextMatch);			
			}
			
			entity.setRoundNo(dto.getRoundNo());
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("MatchConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("MatchConverter: toEntity: has exception");
			return null;
		}
		System.out.println("MatchConverter: toEntity: finish");
		return entity;
	}

	public MatchDTO toDTO(MatchEntity entity){
		System.out.println("MatchConverter: toDTO: finish");
		MatchDTO dto = new MatchDTO();
		try {
			dto.setNumOfSet(entity.getNumOfSet());
			
			String expectedDate = validator.formatDateToString(entity.getExpectedDate());
			dto.setExpectedDate(expectedDate);
			
			dto.setExpectedPlace(entity.getExpectedPlace());
			
			String realDate = validator.formatDateToString(entity.getRealDate());
			dto.setRealDate(realDate);
			
			dto.setRealPlace(entity.getRealPlace());
			
			dto.setCompetitionId(entity.getCompetition().getId());
			dto.setNextMatchId(entity.getNextMatch().getId());
			
			dto.setRoundNo(entity.getRoundNo());
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("MatchConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("MatchConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("MatchConverter: toDTO: finish");
		return dto;
	}

}
