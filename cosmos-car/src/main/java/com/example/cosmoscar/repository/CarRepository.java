package com.example.cosmoscar.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.cosmoscar.Entity.Car;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

@Repository
public interface CarRepository extends CosmosRepository<Car, String> {

	List<Car> findCarByType(String type);

}
