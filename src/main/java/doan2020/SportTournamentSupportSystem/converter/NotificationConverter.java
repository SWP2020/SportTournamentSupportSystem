package doan2020.SportTournamentSupportSystem.converter;

import doan2020.SportTournamentSupportSystem.dto.NotificationDTO;
import doan2020.SportTournamentSupportSystem.entity.NotificationEntity;

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
