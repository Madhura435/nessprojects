package com.madhura.factory.pattern.impl;

import java.awt.Polygon;

import org.springframework.stereotype.Component;

import com.madhura.factory.pattern.factory.Polygon1;

@Component
public class Rectangle implements Polygon1 {

	@Override
	public String type() {
		return "recatngle";
	}

	@Override
	public Integer area() {
		int a=6,b=9;
		return a*b;
	}

	
}
