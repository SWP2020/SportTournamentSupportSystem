package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CommentDTO;
import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IPostService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Component
public class CommentConverter {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPostService postService;
	
	public CommentEntity toEntity(CommentDTO dto) {
		System.out.println("CommentConverter: toEntity: start");
		CommentEntity entity = new CommentEntity();
		try {
			if (dto.getAuthorId() != null) {
				Long authorId = dto.getAuthorId();
				UserEntity author = userService.findOneById(authorId);
				entity.setAuthor(author);
			}
			
			if (dto.getPostId() != null) {
				Long postId = dto.getPostId();
				PostEntity post = postService.findOneById(postId);
				entity.setPost(post);
			}
			
			entity.setContent(dto.getContent());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("CommentConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("CommentConverter: toEntity: has exception");
			return null;
		}
		System.out.println("CommentConverter: toEntity: finish");
		return entity;
	}
	
	public CommentDTO toDTO(CommentEntity entity) {
		System.out.println("CommentConverter: toDTO: start");
		CommentDTO dto = new CommentDTO();
		try {
			dto.setId(entity.getId());
			dto.setAuthorId(entity.getAuthor().getId());
			dto.setPostId(entity.getPost().getId());
			dto.setContent(entity.getContent());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("CommentConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("CommentConverter: toDTO: has exception");
			return null;
		}
		System.out.println("CommentConverter: toDTO: finish");
		return dto;
	}

}
