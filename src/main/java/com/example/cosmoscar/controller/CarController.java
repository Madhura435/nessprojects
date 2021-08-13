package com.example.cosmoscar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cosmoscar.Entity.Car;
import com.example.cosmoscar.service.CarService;

@RestController
//@RequestMapping("/api/car")
public class CarController {
	@Autowired
	public CarService carService;
	@PostMapping("/saveCar")
	public void saveCollege(@RequestBody Car car)
	{
		carService.saveCar(car);
	}
	@GetMapping("/getCar/{id}/colour/{colour}")
	public Optional<Car> getCollege(@PathVariable String id,@PathVariable String colour)
	{
		return carService.getCarById(id,colour);
}
	@GetMapping("/getCars")
	public List<Car> getColleges(@RequestParam String type)
	{
		return carService.getCars(type);
	}
	
	@DeleteMapping("/delete/{id}/colour/{colour}")
	public void deleteCollege(@PathVariable String id,@PathVariable String colour)
	{
		carService.deleteCar(id,colour);
	}
	
}
