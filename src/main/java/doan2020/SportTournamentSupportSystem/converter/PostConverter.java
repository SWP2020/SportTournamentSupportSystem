package doan2020.SportTournamentSupportSystem.converter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtOut.PostDtOut;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Component
public class PostConverter{
	
	@Autowired
	ITournamentService tournamentService;
	
	@Autowired
	IUserService userService;
	
	public PostEntity toEntity(Map<String, Object> map) throws Exception{
		PostEntity entity = new PostEntity();
		System.out.println("In toEntity:");
		try {
			entity.setTitle((String) map.get("title"));
			
			entity.setContent((String) map.get("content"));
			
			Long tournament_id = Long.parseLong(String.valueOf(map.get("tournamentId")));
			TournamentEntity tournament = tournamentService.findOneById(tournament_id);
			entity.setTournament(tournament);
			
			Long author_id = Long.parseLong(String.valueOf(map.get("authorId")));
			UserEntity author = userService.findOneById(author_id);
			entity.setAuthor(author);
		}catch (Exception e) {
			System.out.println("Has Exception");
			throw e;
		}
		System.out.println("Out toEntity with no Exception");
		return entity;
	}

	public PostDtOut toDTO(PostEntity entity) throws Exception {
		
		PostDtOut dto = new PostDtOut();
		try {
			dto.setId(entity.getId());
			dto.setTitle(entity.getTitle());
			dto.setContent(entity.getContent());
			dto.setTournamentId(entity.getTournament().getId());
			dto.setAuthorId(entity.getAuthor().getId());
		} catch (Exception e) {
			throw e;
		}
		
		return dto;
	}

}
