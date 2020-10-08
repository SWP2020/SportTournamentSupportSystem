package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.dto.GroupStageSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IFormatService;

@Component
public class GroupStageSettingConverter {

	@Autowired
	private IFormatService formatService;

	@Autowired
	private ITournamentService TournamentService;

	public GroupStageSettingEntity toEntity(GroupStageSettingDTO dto) {
		System.out.println("GroupStageSettingConverter: toEntity: start");
		GroupStageSettingEntity entity = new GroupStageSettingEntity();
		try {
			entity.setHasHomeMatch(dto.isHasHomeMatch());
			if (dto.getBo() != null) {
				entity.setBo(dto.getBo());
			}
			if (dto.getFormatId() != null) {
				Long formatId = dto.getFormatId();
				FormatEntity format = formatService.findOneById(formatId);
				entity.setFormat(format);
			} else {
				String name = Const.ROUND_ROBIN_FORMAT;
				FormatEntity format = formatService.findByName(name);
				entity.setFormat(format);
			}

			if (dto.getTournamentId() != null) {
				TournamentEntity Tournament = TournamentService.findOneById(dto.getTournamentId());
				entity.setTournament(Tournament);
			}
			if (dto.getMaxTeamPerTable() != null) {
				entity.setMaxTeamPerTable(dto.getMaxTeamPerTable());
			}
			if (dto.getAdvanceTeamPerTable() != null) {
				entity.setAdvanceTeamPerTable(dto.getAdvanceTeamPerTable());
			}
			
			if (dto.getTotalTable() != null) {
				entity.setTotalTable(dto.getTotalTable());
			}

			System.out.println("GroupStageSettingConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("GroupStageSettingConverter: toEntity: has exception");
			return null;
		}
		System.out.println("GroupStageSettingConverter: toEntity: finish");
		return entity;
	}

	public GroupStageSettingDTO toDTO(GroupStageSettingEntity entity) {
		System.out.println("GroupStageSettingConverter: toDTO: finish");
		GroupStageSettingDTO dto = new GroupStageSettingDTO();
		try {
			dto.setId(entity.getId());
			System.out.println(dto.getBo());

			dto.setBo(entity.getBo());

			if (entity.getFormat() != null)
				dto.setFormatId(entity.getFormat().getId());
			if (entity.getTournament() != null)
				dto.setTournamentId(entity.getTournament().getId());
			dto.setHasHomeMatch(entity.isHasHomeMatch());
			dto.setMaxTeamPerTable(entity.getMaxTeamPerTable());
			dto.setAdvanceTeamPerTable(entity.getAdvanceTeamPerTable());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			dto.setTotalTable(entity.getTotalTable());
			System.out.println("GroupStageSettingConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("GroupStageSettingConverter: toDTO: has exception");
			return null;
		}

		System.out.println("GroupStageSettingConverter: toDTO: finish");
		return dto;
	}
}
