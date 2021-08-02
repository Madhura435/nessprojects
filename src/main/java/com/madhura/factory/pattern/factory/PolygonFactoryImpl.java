package com.madhura.factory.pattern.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolygonFactoryImpl {

 @Autowired 
 private List<Polygon1> polygonList; 
 private static final Map<String,Polygon1> polygonMap=new HashMap<String,Polygon1>();

@PostConstruct 
private void initMap() { 
	polygonList.stream().forEach(polygon->polygonMap.put(polygon.type(),polygon)); 
	} 
public static Polygon1 createInstance(String polygon) throws Exception { 
	return Optional.ofNullable(polygonMap.get(polygon)).orElseThrow(()-> new
 IllegalArgumentException("Invalid Polygon")); 
 }

}
