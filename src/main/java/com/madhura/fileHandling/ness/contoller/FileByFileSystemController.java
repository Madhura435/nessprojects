package com.madhura.fileHandling.ness.contoller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.util.StreamUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.madhura.fileHandling.ness.service.FileByFileSystemService;

/**
 * This is the APIclass used to store/download single/multiple files.
 * @author Madhura
 *@see <a href="http://localhost/9761/file">http://localhost/9761/file</a>
 */
@RestController
@RequestMapping("/file")
public class FileByFileSystemController {
/**
 * This is the fileService class autowired. This controller class uses internally this class.
 */
@Autowired	
private FileByFileSystemService fileService;
/**
 * This is the method used to store single file in local system.
 * @param file please provide key as file and select the file you need to upload.
 * @return FileResponse class where you can find uploaded file details 
 * @throws IOException IOException if file not selected properly, not select any file
 * @see <a href="http://localhost/9761/file//single/upload">http://localhost/9761/file//single/upload</a>
 */
@PostMapping("/single/upload")
public FileResponse SingleFileUpload(@RequestParam("file") MultipartFile file) throws IOException
{
	String FileName=fileService.singleFileStore(file);
	String Upoladedurl=ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/file/download/").path(FileName).toUriString();
	
	String contentType=file.getContentType();
	FileResponse fileResponse=new FileResponse(FileName,contentType,Upoladedurl);
	
	return fileResponse;
}
/**
 * This is method used to download single file from the localFile system.
 * @param fileName please provide your file name along with url like http://localhost:9761//file//downloa/?fileName=real nav.html 
 * @param request it is HttpServletRequest contains all request information. Here used to get MediaType.
 * @return Status,ResponseEntity<Resource>,your downloaded file
 * @see <a href="http://localhost/9761/file//download/{fileName}">http://localhost/9761/file//download/{fileName}</a>
 */
@GetMapping("/download/{fileName}")
public ResponseEntity<Resource> downloadSingleFile(@PathVariable String fileName,
		HttpServletRequest request)
{
	Resource resource=fileService.downloadSingleFile(fileName);
	String mimeType;
	try {
		mimeType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	} catch (IOException e) {
		 mimeType=MediaType.APPLICATION_PDF_VALUE;
	}
	//MediaType contentType=MediaType.ALL;
	MediaType contentType=MediaType.parseMediaType(mimeType);
	
	return ResponseEntity.ok().contentType(contentType)
			//header(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName="+resource.getFilename()).
			.header(HttpHeaders.CONTENT_DISPOSITION,"inline;fileName="+resource.getFilename())
			.body(resource);

}
/**
 * This is the method used to upload multiple files in local file System.
 * @param files please give key as files, select multiple files you need to upload
 * @return list of FileResponse class where you can find upload files details
 * @see <a href="http://localhost/9761/file///multifile/upload">http://localhost/9761/file///multifile/upload</a>
 *<p>Here iterating of Arrays for every iteration you upload single file. 
 *Atlast you get list of file responses</p>
 */
@PostMapping("/multifile/upload")
public List<FileResponse> multiFileUpload(@RequestParam("files") MultipartFile[] files) 
{
	List<FileResponse> listofresponse=new ArrayList<FileResponse>();
	Arrays.asList(files).stream().forEach(file->
	{
		String FileName=fileService.singleFileStore(file);
		
		String Upoladedurl=ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/file/download/").path(FileName).toUriString();
		
		String contentType=file.getContentType();
		FileResponse fileResponse=new FileResponse(FileName,contentType,Upoladedurl);
		listofresponse.add(fileResponse);
	}
	     );
	
	return listofresponse;
	
}
/**
 * This is the method used to download multiple files as Zip format.
 * @param files please provide filenames which you need to download along with url
 * @param response response status
 * @throws IOException it gives IOException when something wrong in reading files etc
 */
@GetMapping("/zipdownload")
public void  multiFileDownload(@RequestParam("fileName") String[] files,
		HttpServletResponse response) throws IOException
{
ZipOutputStream zos=new ZipOutputStream(response.getOutputStream());
Arrays.asList(files).stream().forEach(file->
{
	Resource resource=fileService.downloadSingleFile(file);
	ZipEntry zipEntry=new ZipEntry(resource.getFilename());
	try {
		zipEntry.setSize(resource.contentLength());
		zos.putNextEntry(zipEntry);
		StreamUtils.copy(resource.getInputStream(),zos);
		zos.closeEntry();
	    } 
	catch (IOException e) {
		e.printStackTrace();
             }
}
);
zos.finish();
zos.close();
response.setStatus(200);
response.addHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName=zifFile");
}

}




