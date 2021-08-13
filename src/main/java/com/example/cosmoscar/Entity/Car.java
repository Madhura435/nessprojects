package com.example.cosmoscar.Entity;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "cars")
public class Car {
	
	@Id
	private String carId;

	private String name;
	
	private String type;
	
	@PartitionKey
	private String colour;
	/**
	{
 "carId":"1",
 "name":"audi",
"type":"BW",
"colour":"green"
}
	 */
}
