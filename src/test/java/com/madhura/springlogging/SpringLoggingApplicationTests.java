package com.madhura.springlogging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringLoggingApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringLoggingApplicationTests {
	@LocalServerPort
	private int port;
	TestRestTemplate testRestTemplate=new TestRestTemplate();
	HttpHeaders httpHeaders=new HttpHeaders();
	Map<String,String> haspmap=new HashMap();
	@Test
	public void getMeassge() throws Exception
	{
		String url = "http://localhost:8000/logs/measgge";
		ResponseEntity<String> responseEntity=testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),200);
		String output=responseEntity.getBody();
		Assertions.assertEquals(output,"you can check logs of this appiliction either in console or log file");
	}

}
