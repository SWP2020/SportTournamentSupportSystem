package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionFormatService;
import doan2020.SportTournamentSupportSystem.service.ISportService;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;

@Component
public class CompetitionConverter {
	
	@Autowired
	private ITournamentService tournamentService;
	
	@Autowired
	private ICompetitionFormatService competitionFormatService;
	
	@Autowired
	private ISportService sportService;
	
	public CompetitionEntity toEntity(CompetitionDTO dto){
		System.out.println("CompetitionConverter: toEntity: start");
		CompetitionEntity entity = new CompetitionEntity();
		try {
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			
			Long competitionTournamentId = dto.getTournamentId();
			TournamentEntity competitionTournament = tournamentService.findOneById(competitionTournamentId);
			entity.setTournament(competitionTournament);
			
            Long competitionSportId = dto.getSportId();
			SportEntity competitionSport = sportService.findOneById(competitionSportId);
			entity.setSport(competitionSport);
			
			Long competitionMainFormatId = dto.getMainFormatId();
			CompetitionFormatEntity competitionMainFormat = competitionFormatService.findOneById(competitionMainFormatId);
			entity.setMainFormat(competitionMainFormat);
			
			entity.setGroupStage(dto.getGroupStage());
			
			Long competitionGroupStageFormatId = dto.getMainFormatId();
			CompetitionFormatEntity competitionGroupStageFormat = competitionFormatService.findOneById(competitionGroupStageFormatId);
			entity.setGroupStageFormat(competitionGroupStageFormat);
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("CompetitionConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("CompetitionConverter: toEntity: has exception");
			return null;
		}
		System.out.println("CompetitionConverter: toEntity: finish");
		return entity;
	}

	public CompetitionDTO toDTO(CompetitionEntity entity){
		System.out.println("CompetitionConverter: toDTO: finish");
		CompetitionDTO dto = new CompetitionDTO();
		try {
			
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			
			TournamentEntity competitionTournament = entity.getTournament();
			Long competitionTournamentId = competitionTournament.getId();
			dto.setTournamentId(competitionTournamentId);
			
			SportEntity competitionSport = entity.getSport();
			Long competitionSportId = competitionSport.getId();
			dto.setSportId(competitionSportId);
			
			CompetitionFormatEntity competitionMainFormat = entity.getMainFormat();
			Long competitionMainFormatId = competitionMainFormat.getId();
			dto.setMainFormatId(competitionMainFormatId);
			
			dto.setGroupStage(entity.getGroupStage());
			
			CompetitionFormatEntity competitionGroupStageFormat = entity.getGroupStageFormat();
			Long competitionGroupStageFormatId = competitionGroupStageFormat.getId();
			dto.setGroupStageFormatId(competitionGroupStageFormatId);
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("CompetitionConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionConverter: toDTO: has exception");
			throw e;
		}
		
		System.out.println("CompetitionConverter: toDTO: finish");
		return dto;
	}
}
