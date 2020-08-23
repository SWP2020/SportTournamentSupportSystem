package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.MatchDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class MatchConverter {

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private IMatchService matchService;

	@Autowired
	private ITeamService teamService;

	@Autowired
	private Validator validator;

	public MatchEntity toEntity(MatchDTO dto) {
		System.out.println("MatchConverter: toEntity: start");
		MatchEntity entity = new MatchEntity();
		try {
			entity.setName(dto.getName());
			entity.setLocation(dto.getLocation());

			if (dto.getLoserId() != null) {
				TeamEntity loser = teamService.findOneById(dto.getLoserId());
				entity.setLoser(loser);
			}

			if (dto.getTeam1Id() != null) {
				TeamEntity team1 = teamService.findOneById(dto.getTeam1Id());
				entity.setTeam1(team1);
			}
			entity.setTeam1Bonus(dto.getTeam1Bonus());
			if (dto.getTeam2Id() != null) {
				TeamEntity team2 = teamService.findOneById(dto.getTeam2Id());
				entity.setTeam2(team2);
			}
			entity.setTeam2Bonus(dto.getTeam2Bonus());
			Date expectedDate = validator.formatStringToDate(dto.getTime());
			entity.setTime(expectedDate);

			if (dto.getCompetitionId() != null) {
				Long competitionId = dto.getCompetitionId();
				CompetitionEntity competition = competitionService.findOneById(competitionId);
				entity.setCompetition(competition);
			}
            
			if (dto.getWinnerId() != null) {
				TeamEntity winner = teamService.findOneById(dto.getLoserId());
				entity.setWinnner(winner);
			}

			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			
			System.out.println("MatchConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("MatchConverter: toEntity: has exception");
			return null;
		}
		System.out.println("MatchConverter: toEntity: finish");
		return entity;
	}

	public MatchDTO toDTO(MatchEntity entity) {
		System.out.println("MatchConverter: toDTO: finish");
		MatchDTO dto = new MatchDTO();
		try {
			dto.setId(entity.getId());
			dto.setCompetitionId(entity.getCompetition().getId());
			dto.setLocation(entity.getLocation());
			dto.setLoserId(entity.getLoser().getId());
			dto.setName(entity.getName());
			dto.setStatus(entity.getStatus());
			dto.setTeam1Bonus(entity.getTeam1Bonus());
			dto.setTeam1Id(entity.getTeam1().getId());
			dto.setTeam2Bonus(entity.getTeam2Bonus());
			dto.setTeam2Id(entity.getTeam2().getId());
			String time = validator.formatDateToString(entity.getTime());
			dto.setTime(time);
			dto.setUrl(entity.getUrl());
			dto.setWinnerId(entity.getWinnner().getId());
			System.out.println("MatchConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("MatchConverter: toDTO: has exception");
			return null;
		}

		System.out.println("MatchConverter: toDTO: finish");
		return dto;
	}

}
