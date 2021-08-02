package com.madhura.factory.pattern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.madhura.factory.pattern.factory.Polygon1;
import com.madhura.factory.pattern.factory.PolygonFactoryImpl;

@RestController
public class FactoryPatternController {
@GetMapping("/{polygon-type}")
public Integer getPolygonArea(@PathVariable("polygon-type") String polygon1) throws Exception
{
	Polygon1 polygonFactory=PolygonFactoryImpl.createInstance(polygon1);
	return polygonFactory.area();
	
}
}
