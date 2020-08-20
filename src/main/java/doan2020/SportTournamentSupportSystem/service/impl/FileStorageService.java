package doan2020.SportTournamentSupportSystem.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;

@Service
public class FileStorageService implements IFileStorageService {

	public Path getFileStorageLocation(String endFolderName) throws Exception {
		Path fileStorageLocation = Paths.get(Const.PATH_RESOURCE + endFolderName).toAbsolutePath().normalize();

		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception ex) {
			return null;
		}

		return fileStorageLocation;
	}

	@Override
	public String storeFileImage(MultipartFile file, String name, String type) {
		Path fileStorageLocation = null;
		try {

			fileStorageLocation = getFileStorageLocation(Const.IMAGE_FOLDER + "\\" + name);

			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return null;
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = null;
			if(type==Const.AVATAR) {
				targetLocation = fileStorageLocation
						.resolve(FilenameUtils.getBaseName(Const.AVATAR) + "." + FilenameUtils.getExtension(fileName));
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			}else if(type == Const.BACKGROUND){
				targetLocation = fileStorageLocation
						.resolve(FilenameUtils.getBaseName(Const.BACKGROUND) + "." + FilenameUtils.getExtension(fileName));
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			}
			
			return targetLocation.toString();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public String storeFileBranch(MultipartFile file) {
		Path fileStorageLocation = null;
		try {
//			fileStorageLocation = getFileStorageLocation(Const.BRANCH_CONFIG_FOLDER);

		} catch (Exception ex) {
			return null;
		}
		return null;
	}

//	@Override
//	public Resource loadFileAsResource(String fileName) throws Exception {
//		try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if(resource.exists()) {
//                return resource;
//            } else {
//                throw new Exception("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new Exception("File not found " + fileName, ex);
//        }
//	}

}
