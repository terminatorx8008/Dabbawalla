package com.company.dabawalla.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.company.dabawalla.dao.MessImagesRepo;
import com.company.dabawalla.entities.Mess;
import com.company.dabawalla.entities.MessImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Value("${mess.images.directory}")
    private String messImagesDirectory;
    @Autowired
    private MessImagesRepo messImagesRepo;

    public void deleteMessImagesFromStorage(List<MessImages> messImages) {
        for (MessImages messImage : messImages) {
            try {
                Path fileToDeletePath = Paths.get(messImagesDirectory, messImage.getMessImage());
                Files.deleteIfExists(fileToDeletePath);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception as per your application's requirements
            }
        }
    }
    public void addMessImagesToStorage(List<MultipartFile> files, Mess oldMess){
        for (MultipartFile file : files) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                File saveFile = new ClassPathResource(messImagesDirectory).getFile();
                Path path = Path.of(saveFile.getAbsolutePath() + File.separator + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                MessImages messImages = new MessImages();
                oldMess.getMessImage().add(messImages);
                messImages.setMess(oldMess);
                messImages.setMessImage(fileName);
                messImagesRepo.save(messImages);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

