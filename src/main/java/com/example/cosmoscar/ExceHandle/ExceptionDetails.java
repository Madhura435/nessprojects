package com.example.cosmoscar.ExceHandle;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionDetails {
private Date timeStamp;
private String message;
private String exceptionUri;
}
