package com.musalasoft.application.service;

import java.nio.file.Path;
import java.nio.file.Files;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.application.exception.ResourceNotFoundException;

@Service
public class ImageService {

	public void saveFile(MultipartFile multipartFile, Path path) throws IOException {
		if(multipartFile == null) {		
			throw new ResourceNotFoundException("File is not found in the given path");
		}
		
        File directory=new File(path.toString());
        if(!directory.exists())
            directory.mkdirs();
        Files.write(path.resolve(multipartFile.getOriginalFilename()),
			multipartFile.getBytes());
	}
}
