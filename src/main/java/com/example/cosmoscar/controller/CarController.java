package com.example.cosmoscar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cosmoscar.Entity.Car;
import com.example.cosmoscar.Exception.ResourceNotFoundException;
import com.example.cosmoscar.service.CarService;

@RestController
@RequestMapping("/api/car")
public class CarController {
	@Autowired
	public CarService carService;
	
	@PostMapping("/saveCar")
	public Car saveCar(@RequestBody Car car)
	{
		if(car.getCarId().isEmpty() || car.getName().isEmpty() || car.getType().isEmpty() || car.getColour().isEmpty())
		{
			throw new NullPointerException("input fields canNot be empty");
		}
		
		return carService.saveCar(car);
	}
	
	
	@GetMapping("/getCar/{id}/colour/{colour}")
	public Car getCar(@PathVariable String id,@PathVariable String colour)
	{
		return carService.getCarById(id,colour)
				.orElseThrow(() ->
		new ResourceNotFoundException("car not found with id "+id+"in the partion"+colour));
     }
	
	
	@GetMapping("/getCars/{type}")
	public List<Car> getCars(@PathVariable String type)
	{
		return carService.getCars(type);
	}
	
	
	@PutMapping("/alterCar/{id}/colour/{colour}")
	public Car alterdCar(@PathVariable String id,@RequestBody Car car,@PathVariable String colour)
	{
		Car carById= carService
				  .getCarById(id,colour)
				  .orElseThrow(() ->
		new ResourceNotFoundException("car not found with id "+id+"in the partion"+colour));
	
		carById.setCarId(car.getCarId());
		carById.setName(car.getName());
		carById.setType(car.getType());
		carById.setColour(car.getColour());
		
		return carService.saveCar(carById);
	}
	
	
	@DeleteMapping("/delete/{id}/colour/{colour}")
	public ResponseEntity<Map<String,Boolean>> deleteCar(@PathVariable String id,@PathVariable String colour)
	{
		Car carById= carService
				.getCarById(id,colour)
				.orElseThrow(() ->
		new ResourceNotFoundException("car not found with id "+id+"in the partion"+colour));
		
		carService.deleteCar(id,colour);
		Map<String,Boolean> response=new HashMap();
		response.put("Deleted car wih id "+id+",partion"+colour,Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
}
