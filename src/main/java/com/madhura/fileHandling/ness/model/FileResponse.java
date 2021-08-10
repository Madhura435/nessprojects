package com.madhura.fileHandling.ness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * It is the class used get response when you upload the files in DB or local file System.
 * @author P7112792
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
	/**
	 * It is upload file name
	 */
private String FileName;
/**
 * It is content of file i.e pdf/doc you uploaded
 */
private String ContentType;
/**
 * Here you get download url of the file of upload file 
 */
private String url;
}
