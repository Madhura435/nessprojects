package com.example.cosmoscar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.data.cosmos.PartitionKey;
import com.example.cosmoscar.Entity.Car;
import com.example.cosmoscar.repository.CarRepository;


@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	public Car saveCar(Car car)
	 {
		return carRepository.save(car);
	 }

	public Optional<Car> getCarById(String id,String colour)
	{
		return carRepository.findById(id,new PartitionKey(colour)); 
	}

	public List<Car> getCars(String type)
	{
		return carRepository.findCarByType(type);
	}

	public void deleteCar(String id,String colour)
	{
		carRepository.deleteById(id,new PartitionKey(colour));
	}
    
	public Car alterCar(Car car)
	 {
		return carRepository.save(car);
	 }
}
