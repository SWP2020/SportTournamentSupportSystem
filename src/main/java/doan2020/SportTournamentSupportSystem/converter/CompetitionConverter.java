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
			if (dto.getName() != null)
				entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			
			if (dto.getTournamentId() != null) {
				Long tournamentId = dto.getTournamentId();
				TournamentEntity tournament = tournamentService.findOneById(tournamentId);
				entity.setTournament(tournament);
			}
			
			if (dto.getSportId() != null) {
	            Long sportId = dto.getSportId();
				SportEntity sport = sportService.findOneById(sportId);
				entity.setSport(sport);
			}
			
			if (dto.getMainFormatId() != null) {
				Long mainFormatId = dto.getMainFormatId();
				CompetitionFormatEntity mainFormat = competitionFormatService.findOneById(mainFormatId);
				entity.setMainFormat(mainFormat);
			}
			
			entity.setGroupStage(dto.getGroupStage());
			
			if (entity.getGroupStage() && dto.getGroupStageFormatId() != null) {
				Long groupStageFormatId = dto.getGroupStageFormatId();
				CompetitionFormatEntity groupStageFormat = competitionFormatService.findOneById(groupStageFormatId);
				entity.setGroupStageFormat(groupStageFormat);
			}
			
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
			
			TournamentEntity tournament = entity.getTournament();
			Long tournamentId = tournament.getId();
			dto.setTournamentId(tournamentId);
			
			SportEntity sport = entity.getSport();
			Long sportId = sport.getId();
			dto.setSportId(sportId);
			
			CompetitionFormatEntity mainFormat = entity.getMainFormat();
			Long mainFormatId = mainFormat.getId();
			dto.setMainFormatId(mainFormatId);
			
			dto.setGroupStage(entity.getGroupStage());
			
			CompetitionFormatEntity groupStageFormat = entity.getGroupStageFormat();
			if (groupStageFormat != null) {
				Long groupStageFormatId = groupStageFormat.getId();
				dto.setGroupStageFormatId(groupStageFormatId);
			}
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("CompetitionConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("CompetitionConverter: toDTO: finish");
		return dto;
	}
}
