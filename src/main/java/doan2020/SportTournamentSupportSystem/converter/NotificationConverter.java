package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.NotificationDTO;
import doan2020.SportTournamentSupportSystem.entity.NotificationEntity;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.service.IPostService;

@Component
public class NotificationConverter {
	
	@Autowired
	private IPostService postService;
	
	public NotificationEntity toEntity(NotificationDTO dto) {
		System.out.println("NotificationConverter: toEntity: start");
		NotificationEntity entity = new NotificationEntity();
		try {
			entity.setTitle(dto.getTitle());
			
			if (dto.getPostId() != null) {
				Long postId = dto.getPostId();
				PostEntity post = postService.findOneById(postId);
				entity.setPost(post);
			}
			
			entity.setContent(dto.getContent());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("NotificationConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("NotificationConverter: toEntity: has exception");
			return null;
		}
		System.out.println("NotificationConverter: toEntity: finish");
		return entity;
	}
	
	public NotificationDTO toDTO(NotificationEntity entity) {
		System.out.println("NotificationConverter: toDTO: start");
		NotificationDTO dto = new NotificationDTO();
		try {
			dto.setId(entity.getId());
			dto.setTitle(entity.getTitle());
			dto.setPostId(entity.getPost().getId());
			dto.setContent(entity.getContent());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("NotificationConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("NotificationConverter: toDTO: has exception");
			return null;
		}
		System.out.println("NotificationConverter: toDTO: finish");
		return dto;
	}
}
