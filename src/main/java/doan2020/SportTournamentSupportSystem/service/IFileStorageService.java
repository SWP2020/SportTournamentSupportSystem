package doan2020.SportTournamentSupportSystem.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
	
	public Path getFileStorageLocation(String endFolderName) throws Exception;
	
	public String storeFileImage(MultipartFile file, String name, String type);
	
	public String storeFileBranch(MultipartFile file);
	
	public String saveObjectToFile(Object o, String filePath);
	
	public Object getObjectFromFile(String filePath);
	
//	public Resource loadFileAsResource(String fileName) throws Exception;
}
