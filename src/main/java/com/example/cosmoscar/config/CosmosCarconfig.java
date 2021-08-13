package com.example.cosmoscar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.data.cosmos.CosmosKeyCredential;
import com.microsoft.azure.spring.data.cosmosdb.config.AbstractCosmosConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.CosmosDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableCosmosRepositories;

@Configuration
@EnableCosmosRepositories(basePackages= "com.example.cosmoscar.repository")
public class CosmosCarconfig extends AbstractCosmosConfiguration{
	
	@Value("${azure.cosmosdb.uri}")
	private String uri;
	
	@Value("${azure.cosmosdb.key}")
	private String key;
	
	@Value("${azure.cosmosdb.secondaryKey}")
	private String secondaryKey;
	
	@Value("${azure.cosmosdb.database}")
	private String dbName;
	
	private CosmosKeyCredential cosmosKeyCredintial; 

	@Bean
	public CosmosDBConfig getCosmosConfig1()
	{
		this.cosmosKeyCredintial=new CosmosKeyCredential(key);
		
		CosmosDBConfig cosdbconfig=CosmosDBConfig
				.builder(uri,this.cosmosKeyCredintial,dbName)
				.build();
		
		return cosdbconfig;
	}
}
