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

/**
 * It is service class used by FileByFileSystemController i.e local system files upload/download.
 * @author P7112792
 *<p>where it contains logic of Files upload in local system. 
 *Here same single file upload/download logic used to Multiple file upload/download</p>
 */
@Service
public class FileByFileSystemService {
	/**
	 * It is File storage path used to create directory where you stored the file  
	 */
  private Path FileStorePath;
  /**
   * It is where your file stored in local system
   */
  private String fileStroageLocation;
  
  /**
   * It is used to initialization of fileStroageLocation,FileStorePath,Files directory.
   */
  public FileByFileSystemService(@Value("${file.storage.location:temp}") String fileLocation) throws IOException
	{
		this.fileStroageLocation=fileLocation;
		FileStorePath=Paths.get(fileStroageLocation).toAbsolutePath().normalize();
		Files.createDirectories(FileStorePath);
	}
  
  
	/**
	 * It is the method used to single file stored in local system.
	 * @param file is the multipart file 
	 * @return string contains filename of uploaded file
	 */
	public String singleFileStore(MultipartFile file) 
	{
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		Path storePath=Paths.get(FileStorePath+"//"+fileName);
		try {
			Files.copy(file.getInputStream(),storePath,StandardCopyOption.REPLACE_EXISTING);
		    } 
		catch (IOException e) {
			
			throw new RuntimeException("Exception when storing file");
		}
		
		return fileName;
	}
	
	
	/**
	 * It is the method used to download single file from local system
	 * @param fileName it is file name you need to download
	 * @return Resource i.e it contains download file
	 */
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





