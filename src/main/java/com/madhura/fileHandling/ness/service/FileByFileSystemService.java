package com.madhura.fileHandling.ness.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileByFileSystemService {
  private Path FileStorePath;
  private String fileStroageLocation;
	public FileByFileSystemService(@Value("${file.storage.location:temp}") String fileLocation) 
			throws IOException
	{
		this.fileStroageLocation=fileLocation;
		FileStorePath=Paths.get(fileStroageLocation).toAbsolutePath().normalize();
		Files.createDirectories(FileStorePath);
	}
	public String singleFileStore(MultipartFile file) 
	{
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		Path storePath=Paths.get(FileStorePath+"//"+fileName);
		try {
			Files.copy(file.getInputStream(),storePath,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			
			throw new RuntimeException("Exception when storing file");
		}
		return fileName;
	}
	public Resource downloadSingleFile(String fileName)
	{
		Path downloadPath=Paths.get(fileStroageLocation).resolve(fileName);
		Resource resource;
		try {
			resource=new UrlResource(downloadPath.toUri());
		} catch (MalformedURLException e) {
			throw new RuntimeException("issue in reading the file");
		}
		if(resource.exists() && resource.isReadable())
		{
		return resource;	
		}
		else
		{
			throw new RuntimeException("issue in reading the file or file not exist");
		}
	}
}





