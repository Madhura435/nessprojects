package com.madhura.fileHandling.ness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
private String FileName;
private String ContentType;
private String url;
}
