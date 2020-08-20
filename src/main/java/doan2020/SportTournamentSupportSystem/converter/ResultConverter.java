package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ResultDTO;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.ResultEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;

@Component
public class ResultConverter {
	
	@Autowired
	private ITeamService teamService;
	
	@Autowired
	private IMatchService matchService;
	
	public ResultEntity toEntity(ResultDTO dto){
		System.out.println("ResultConverter: toEntity: start");
		ResultEntity entity = new ResultEntity();
		try {
			if (dto.getMatchId() != null) {
				Long matchId = dto.getMatchId();
				MatchEntity match = matchService.findOneById(matchId);
				entity.setMatch(match);			
			}
			
			if (dto.getTeamId() != null) {
				Long teamId = dto.getTeamId();
				TeamEntity team = teamService.findOneById(teamId);
				entity.setTeam(team);
			}
			
			entity.setSetNo(dto.getSetNo());
			entity.setScore(dto.getScore());
			entity.setRank(dto.getRank());

			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("ResultConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("ResultConverter: toEntity: has exception");
			return null;
		}
		System.out.println("ResultConverter: toEntity: finish");
		return entity;
	}

	public ResultDTO toDTO(ResultEntity entity){
		System.out.println("ResultConverter: toDTO: finish");
		ResultDTO dto = new ResultDTO();
		try {
			dto.setMatchId(entity.getMatch().getId());
			dto.setTeamId(entity.getTeam().getId());
			
			dto.setSetNo(entity.getSetNo());
			dto.setScore(entity.getScore());
			dto.setRank(entity.getRank());
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("ResultConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("ResultConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("ResultConverter: toDTO: finish");
		return dto;
	}
}
