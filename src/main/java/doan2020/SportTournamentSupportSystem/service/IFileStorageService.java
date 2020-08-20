package doan2020.SportTournamentSupportSystem.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
	
	public String storeFile(MultipartFile file);
	
	public String saveObjectToFile(Object o, String filePath);
	
	public Object getObjectFromFile(String filePath);
	
//	public Resource loadFileAsResource(String fileName) throws Exception;
}
