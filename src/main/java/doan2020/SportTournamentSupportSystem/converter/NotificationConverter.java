package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.NotificationDTO;
import doan2020.SportTournamentSupportSystem.entity.NotificationEntity;

@Component
public class NotificationConverter {
	
	public NotificationEntity toEntity(NotificationDTO notificationDTO) {
		NotificationEntity entity = new NotificationEntity();
		return entity;
	}
	
	public NotificationDTO toDTO(NotificationEntity notificationEntity) {
		NotificationDTO dto = new NotificationDTO();
		return dto;
	}
}
