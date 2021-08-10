package com.madhura.fileHandling.ness.contoller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.madhura.fileHandling.ness.model.FileResponse;
import com.madhura.fileHandling.ness.model.Filedocument;
import com.madhura.fileHandling.ness.service.FileByDatabaseService;

/**
 * This is the Api database controller class used to upload/download single file.
 * @author Madhura
 *@see <a href="http://localhost/9761/DBfile">http://localhost/9761/DBfile</a>
 *
 */
@RestController
@RequestMapping("/DBfile")
public class FileByDatabaseController {
	/**
	 * This is the Database Service class autowired.
	 */
@Autowired	
private FileByDatabaseService fileByDatabaseService;
/**
 * This is method used to store single file in database.
 * <p>This method store FileDocument entity in Database. where FileDocument contains SIngleFile column</p>
 * @param file please provide key is file and select here your file need to upload
 * @return FileResponse class where you can find uploaded file details 
 * @throws IOException if file not selected properly, not select any file
 * @see <a href="http://localhost/9761/DBfile//single/uploadDb">http://localhost/9761/DBfile//single/uploadDb</a>
 */
@PostMapping("/single/uploadDb")
public FileResponse SingleFileUpload(@RequestParam("file") MultipartFile file) throws IOException
{
	String FileName=fileByDatabaseService.singleFileStoreDd(file);
	String downloadedurl=ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/DBfile/downloadDb/")
			.path(FileName)
			.toUriString();
	String contentType=file.getContentType();
	
	FileResponse fileResponse=new FileResponse(FileName,contentType,downloadedurl);
	
	return fileResponse;
}
/**
 * This method used to get single file from the database.
 * <p>If provide fileName you can get File</p>
 * @param fileName please provide your filename along with url like http://localhost:9761//DBfile//downloadDb/?fileName=real nav.html 
 * @param request it is HttpServletRequest contains all request information. Here used to get MediaType.
 * @return status code, your file downloaded
 * @see <a href="http://localhost/9761/DBfile//single//downloadDb/{fileName}">http://localhost/9761/DBfile//single//downloadDb/{fileName}</a>
 */
@GetMapping("/downloadDb/{fileName}")
public ResponseEntity<byte[]> downloadSingleFile(@PathVariable String fileName,HttpServletRequest request)
{
	Filedocument document=fileByDatabaseService.downloadFileDB(fileName);
	String mimeType=request.getServletContext().getMimeType(document.getFilename());
	MediaType contentType=MediaType.parseMediaType(mimeType);
	
	return ResponseEntity.ok().contentType(contentType)
			//header(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName="+document.getFilename()).
			.header(HttpHeaders.CONTENT_DISPOSITION,"inline;fileName="+document.getFilename())
			.body(document.getDocfile());
}

}