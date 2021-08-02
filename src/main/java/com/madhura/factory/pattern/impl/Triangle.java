package com.madhura.factory.pattern.impl;

import org.springframework.stereotype.Component;

import com.madhura.factory.pattern.factory.Polygon1;

@Component
public class Triangle implements Polygon1{

	@Override
	public String type() {
		return "triangle";
	}

	@Override
	public Integer area() {
		return 7*6/2;
	}

}
