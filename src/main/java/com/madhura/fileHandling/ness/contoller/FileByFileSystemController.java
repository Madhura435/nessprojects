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

@RestController
@RequestMapping("/file")
public class FileByFileSystemController {
@Autowired	
private FileByFileSystemService fileService;
@PostMapping("/single/upload")
public FileResponse SingleFileUpload(@RequestParam("file") MultipartFile file) throws IOException
{
	String FileName=fileService.singleFileStore(file);
	String Upoladedurl=ServletUriComponentsBuilder.fromCurrentContextPath().
			path("/file/download/").path(FileName).toUriString();
	String contentType=file.getContentType();
	
	FileResponse fileResponse=new FileResponse(FileName,contentType,Upoladedurl);
	return fileResponse;
}
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
	return ResponseEntity.ok().contentType(contentType).
			//header(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName="+resource.getFilename()).
			header(HttpHeaders.CONTENT_DISPOSITION,"inline;fileName="+resource.getFilename()).
			body(resource);

}
@PostMapping("/multifile/upload")
public List<FileResponse> multiFileUpload(@RequestParam("files") MultipartFile[] files) 
{
	List<FileResponse> listofresponse=new ArrayList<FileResponse>();
	Arrays.asList(files).stream().forEach(file->{
		String FileName=fileService.singleFileStore(file);
		String Upoladedurl=ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/file/download/").path(FileName).toUriString();
		String contentType=file.getContentType();
		FileResponse fileResponse=new FileResponse(FileName,contentType,Upoladedurl);
		listofresponse.add(fileResponse);
	}
			);
	
	return listofresponse;
	
}
@GetMapping("/zipdownload")
public void  multiFileDownload(@RequestParam("fileName") String[] files,
		HttpServletResponse response) throws IOException
{
ZipOutputStream zos=new ZipOutputStream(response.getOutputStream());
Arrays.asList(files).stream().forEach(file->{
	Resource resource=fileService.downloadSingleFile(file);
	ZipEntry zipEntry=new ZipEntry(resource.getFilename());
	try {
		zipEntry.setSize(resource.contentLength());
		zos.putNextEntry(zipEntry);
		StreamUtils.copy(resource.getInputStream(),zos);
		zos.closeEntry();
	} catch (IOException e) {
		e.printStackTrace();
	}
		});
}

}




