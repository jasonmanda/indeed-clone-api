package com.dash.it.solution;

import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class FileStorageService {

    private Path fileStorageLocation;
    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
        this.createFolder();
    }

    private void createFolder() {
        this.fileStorageLocation = Paths.get(this.fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {

            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            // throw new Exception("Could not create the directory where the uploaded files
            // will be stored.:"+ex.getMessage());
        }
    }
    private void createFolder(String dir) {
        this.fileStorageLocation = Paths.get(this.fileStorageProperties.getUploadDir()+dir).toAbsolutePath().normalize();

        try {

            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            // throw new Exception("Could not create the directory where the uploaded files
            // will be stored.:"+ex.getMessage());
        }
    }

    public String storeFile(MultipartFile file,String dir) {
        this.createFolder(dir);
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

    public Resource loadFileAsResource(String fileName,String dir) {
        try {
        this.createFolder(dir);
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

    // public Path getFileStorageLocation() {
    //     return this.fileStorageLocation;
    // }
}