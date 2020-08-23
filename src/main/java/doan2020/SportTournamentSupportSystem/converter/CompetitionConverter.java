package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.service.IFinalStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.ISportService;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;

@Component
public class CompetitionConverter {

	@Autowired
	private ITournamentService tournamentService;

	@Autowired
	private IFinalStageSettingService finalStageSettingService;

	@Autowired
	private IGroupStageSettingService groupStageSettingService;

	@Autowired
	private ISportService sportService;

	public CompetitionEntity toEntity(CompetitionDTO dto) {
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

			if (dto.getFinalStageSettingId() != null) {
				Long finalStageSettingId = dto.getFinalStageSettingId();
				FinalStageSettingEntity finalStageSettingEntity = finalStageSettingService
						.findOneById(finalStageSettingId);
				entity.setFinalStageSetting(finalStageSettingEntity);
			}

			if (dto.getGroupStageSettingId() != null) {
				Long groupStageSettingId = dto.getGroupStageSettingId();
				GroupStageSettingEntity groupStageSettingEntity = groupStageSettingService
						.findOneById(groupStageSettingId);
				entity.setGroupStageSetting(groupStageSettingEntity);
			}

			System.out.println("CompetitionConverter: toEntity: CP 1");
			System.out.println("CompetitionConverter: toEntity: CP 2");

			System.out.println("CompetitionConverter: toEntity: CP 3");

			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("CompetitionConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionConverter: toEntity: has exception");
			return null;
		}
		System.out.println("CompetitionConverter: toEntity: finish");
		return entity;
	}

	public CompetitionDTO toDTO(CompetitionEntity entity) {
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

			FinalStageSettingEntity finalStageSettingEntity = entity.getFinalStageSetting();
			if (finalStageSettingEntity != null) {
				Long finalStageSettingId = finalStageSettingEntity.getId();
				dto.setFinalStageSettingId(finalStageSettingId);
			}

			GroupStageSettingEntity groupStageSettingEntity = entity.getGroupStageSetting();
			if (groupStageSettingEntity != null) {
				Long groupStageSettingId = groupStageSettingEntity.getId();
				dto.setGroupStageSettingId(groupStageSettingId);
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
