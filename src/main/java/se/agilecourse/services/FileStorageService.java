package se.agilecourse.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.agilecourse.config.FileStorageProperties;
import se.agilecourse.controller.FileController;
import se.agilecourse.exceptions.FileStorageException;
import se.agilecourse.exceptions.MyFileNotFoundException;


import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;


    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir());
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }



    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public boolean deleteUploadedFile(String filepath){
        String filname = filepath.substring(filepath.lastIndexOf("/") + 1);
        logger.debug("File store location :"+this.fileStorageLocation);
        logger.debug("Deleting file : "+this.fileStorageLocation+File.separator+filname);
        try{
            File file = new File(this.fileStorageLocation+File.separator+filname);
            logger.debug("file Absolute path  : "+file.getAbsolutePath());
            String deletefilepath = file.getAbsolutePath();
            deletefilepath = deletefilepath.replaceAll("/./","/");
            File filetoDeleted = new File(deletefilepath);
            logger.debug("fixed path : "+filetoDeleted.getAbsolutePath());


            if(!filetoDeleted.canWrite()){
                logger.info("No write permissions :");
            }else {
                if (filetoDeleted.exists()) {
                    if (filetoDeleted.delete()) {
                        logger.info(filetoDeleted.getName() + " is deleted!");
                        return true;
                    } else {
                        logger.debug("Delete operation is failed.");
                        return false;
                    }
                } else {
                    logger.debug(filetoDeleted.getName() + " : file not present");
                }
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return true;
    }

    /*public static void main(String[] args) {
        String str = "/Users/junaid/Public/CodeBase/AgileProject/manuals-backend/./uploads/PngIcon.png";
        System.out.println(str.replaceAll("/./","/"));
    }*/

}
