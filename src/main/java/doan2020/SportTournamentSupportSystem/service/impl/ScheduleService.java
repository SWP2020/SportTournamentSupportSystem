package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;

public class ScheduleService {
	
	@Autowired
	private IFileStorageService fileService;
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getSchedule(Long competitionId) {
		String fileName = Const.COMPETITION_SCHEDULING;
		String absFolderPath = null;
		String absFilePath = null;
		HashMap<String, Object> schedule = new HashMap<String, Object>();

		try {

			String folder = Const.COMPETITION_FILESYSTEM + Const.COMPETITION_FOLDER_NAMING + competitionId;
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			absFilePath = absFolderPath + "\\" + fileName;
			schedule = (HashMap<String, Object>) fileService.getObjectFromFile(absFilePath);

		} catch (Exception e) {
		}

		return schedule;
	}

	public String saveSchedule(HashMap<String, Object> schedule, Long competitionId) {
		String fileName = Const.COMPETITION_SCHEDULING;
		String absFolderPath = null;
		String absFilePath = null;

		try {
			String folder = Const.COMPETITION_FILESYSTEM + Const.COMPETITION_FOLDER_NAMING + competitionId;
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			absFilePath = absFolderPath + "\\" + fileName;
			absFilePath = fileService.saveObjectToFile(schedule, absFilePath);

			System.out.println("ScheduleService: saveSchedule: absFileName: \n[" + absFilePath + "]");
		} catch (Exception e) {
		}

		return absFilePath;

	}
	

	public void saveScheduleToDatabase(Long id) {
		HashMap<String, Object> schedule = getSchedule(id);
		String format = (String) schedule.get("Format");
		
		
	}

}
