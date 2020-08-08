package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.PostDTO;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Component
public class PostConverter {

	@Autowired
	IUserService userService;

	@Autowired
	ITournamentService tournamentService;

	public PostEntity toEntity(PostDTO dto) {
		System.out.println("PostConverter: toDTO: finish");
		PostEntity entity = new PostEntity();
		try {
			if(dto.getCreatorId() != null) {
			UserEntity userEntity = userService.findOneById(dto.getCreatorId());
			entity.setCreator(userEntity);

			}
			entity.setContent(dto.getContent());
			entity.setStatus(dto.getStatus());
			entity.setSystemPost(dto.getSystemPost());
			entity.setTitle(dto.getTitle());
			if (dto.getTournamentId() != null) {
				TournamentEntity tournamentEntity = tournamentService.findOneById(dto.getTournamentId());
				entity.setTournament(tournamentEntity);
			}
			entity.setUrl(dto.getUrl());

			System.out.println("PostConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("PostConverter: toDTO: has exception");
			return null;
		}

		System.out.println("PostConverter: toDTO: finish");
		return entity;

	}

	public PostDTO toDTO(PostEntity entity) {
		System.out.println("PostConverter: toDTO: finish");
		PostDTO dto = new PostDTO();
		try {

			dto.setCreatorId(entity.getCreator().getId());

			dto.setContent(entity.getContent());
			dto.setId(entity.getId());
			dto.setStatus(entity.getStatus());
			dto.setSystemPost(entity.getSystemPost());
			dto.setTitle(entity.getTitle());
			dto.setTournamentId(entity.getTournament().getId());
			dto.setUrl(entity.getUrl());

			System.out.println("PostConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("PostConverter: toDTO: has exception");
			return null;
		}

		System.out.println("PostConverter: toDTO: finish");
		return dto;

	}
}
