package com.madhura.factory.pattern.impl;

import org.springframework.stereotype.Component;

import com.madhura.factory.pattern.factory.Polygon1;

@Component
public class Square implements Polygon1 {

	@Override
	public String type() {
	
		return "sqaure";
	}

	@Override
	public Integer area() {
		
		return 5*5;
	}

}
