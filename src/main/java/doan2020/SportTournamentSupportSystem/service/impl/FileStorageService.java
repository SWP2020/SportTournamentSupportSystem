package doan2020.SportTournamentSupportSystem.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.config.FileStorageProperties;
import doan2020.SportTournamentSupportSystem.model.CanSaveToFileObject;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;

@Service
public class FileStorageService implements IFileStorageService{
	
	private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws Exception {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

	@Override
	public String storeFile(MultipartFile file) {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                return null;
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
        	return null;
        }
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
	
	
	
	@Override
	public String saveObjectToFile(Object o, String filePath) {
		
		String finalFilePath = Const.RESOURCE_PATH + filePath;
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(finalFilePath));
			CanSaveToFileObject obj = new CanSaveToFileObject(o);
			oos.writeObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				oos.close();
			} catch (Exception e2) {
				return null;
			}
		}
		
		return finalFilePath;
	}
	
	@Override
	public Object getObjectFromFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

}
