package com.madhura.fileHandling.ness.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.madhura.fileHandling.ness.model.Filedocument;
import com.madhura.fileHandling.ness.repository.FileRepository;
/**
 * It is the DataService class used by FileByDatabaseController class
 * @author P7112792
 *<p>Internal implementation of methods .Where it contains logic of single file upload/download in DB </p>
 */
@Service
public class FileByDatabaseService {
	/**
	 * It is FileRepository class autowired
	 */
	@Autowired
	private FileRepository fileRepository;
	/**
	 * It is the method store single file in database.
	 * @param file provide file you need to upload in DB
	 * @return String contains file name you upload
	 * @throws IOException when file not readable format, not selected file
	 */
	public String singleFileStoreDd(MultipartFile file) throws IOException 
	{
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
        Filedocument fileDocument=new Filedocument();
        fileDocument.setFilename(fileName);
        fileDocument.setDocfile(file.getBytes());
        fileRepository.save(fileDocument);
        return fileName;
	}
	/**
	 * It is method used to find Filedocument based on file name
	 * @param fileName name of the file used to get Filedocument 
	 * @return Filedocument based on file name
	 * @see Filedocument
	 */
	public Filedocument downloadFileDB(String fileName)
	{
		return fileRepository.findByFilename(fileName);
	}
	
}
