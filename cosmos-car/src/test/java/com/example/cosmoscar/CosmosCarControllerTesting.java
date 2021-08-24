package com.example.cosmoscar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;

import com.azure.data.cosmos.PartitionKey;
import com.example.cosmoscar.Entity.Car;
import com.example.cosmoscar.Exception.ResourceNotFoundException;
import com.example.cosmoscar.repository.CarRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CosmosCarControllerTesting {
	@MockBean
	public CarRepository carRepository;

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	ObjectMapper objectmapper = new ObjectMapper();
	private List<Car> mocklistCars;
	private Car createCar = new Car();
	private Car updateCar;
	private Car deleteCar;

	public String burl = "/api/car/";

	@BeforeEach
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		Car car1 = new Car("1", "TN12", "Audi", "skyBlue");
		Car car2 = new Car("2", "AP12", "maruthi", "green");
		Car car3 = new Car("3", "JM12", "BMW", "red");

		mocklistCars = new ArrayList<>(Arrays.asList(car1, car2, car3));

		createCar.setCarId("2");
		createCar.setCarnumber("Dl78");
		createCar.setType("Audi");
		createCar.setColour("red");

		updateCar = new Car("1", "TN12", "Audi", "skyBlue");

		deleteCar = new Car("3", "JM12", "BMW", "red");
	}

	@Test
	public void getCars_200() throws Exception {
		String type = "Audi";
		String url = burl + "getCars/" + type;
		when(carRepository.findCarByType(type)).thenReturn(mocklistCars);

		MvcResult mvcresult = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("TN12"))).andReturn();

		int status = mvcresult.getResponse().getStatus();
		assertEquals(200, status);
		String jsonListCars = mvcresult.getResponse().getContentAsString();
		List<Car> carlist = objectmapper.readValue(jsonListCars, new TypeReference<List<Car>>() {
		});
		assertEquals(carlist, mocklistCars);
		assertTrue(carlist.size() > 0);
	}

	@Test
	public void createCar() throws Exception {
		String url = burl + "saveCar";
		when(carRepository.save(createCar)).thenReturn(createCar);
		String car = objectmapper.writeValueAsString(createCar);

		MvcResult mvcResult = mockMvc.perform(post(url).content(car).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Dl78"))).andReturn();

		String resultedCar = mvcResult.getResponse().getContentAsString();
		Car savedCar = objectmapper.readValue(resultedCar, Car.class);
		createCar.setCarId(savedCar.getCarId());
		assertEquals(createCar, savedCar);
	}

	@Test
	public void getEmployeeById() throws Exception {
		String id = "6";
		String colour = "skyblue";
		String url = burl + "getCar/" + id + "/colour/" + colour;
		Optional<Car> car = Optional.ofNullable(new Car("6", "ke4", "maruthi", "purple"));
		when(carRepository.findById(id, new PartitionKey(colour))).thenReturn(car);

		MvcResult mvcresult = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("ke4"))).andReturn();

		int status = mvcresult.getResponse().getStatus();
		assertEquals(200, status);
		String responseCar = mvcresult.getResponse().getContentAsString();
		Car CarById = objectmapper.readValue(responseCar, Car.class);
		assertEquals(id, CarById.getCarId());
	}

	@Test
	public void updateEmployee() throws Exception {
		String id = "6";
		String colour = "skyblue";
		String url = burl + "getCar/" + id + "/colour/" + colour;
		Optional<Car> car = Optional.ofNullable(new Car("6", "ke4", "maruthi", "purple"));
		when(carRepository.findById(id, new PartitionKey(colour))).thenReturn(car);

		MvcResult mvcresult = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("ke4"))).andReturn();

		String carById = mvcresult.getResponse().getContentAsString();
		Car saveCar = objectmapper.readValue(carById, Car.class);
		saveCar.setCarnumber("Mp78");

		String jsonSavedCar = objectmapper.writeValueAsString(saveCar);
		when(carRepository.save(saveCar)).thenReturn(saveCar);
		String alterUrl = burl + "alterCar/" + id + "/colour/" + colour;

		MvcResult updatemvcResult = mockMvc
				.perform(put(alterUrl).content(jsonSavedCar).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String responseString = updatemvcResult.getResponse().getContentAsString();
		Car ChangedCar = objectmapper.readValue(responseString, Car.class);
		assertEquals("Mp78", ChangedCar.getCarnumber());

	}

	@Test
	public void deleteEmployee() throws Exception {
		String id = "6";
		String colour = "skyblue";
		String url = burl + "delete/" + id + "/colour/" + colour;
		Optional<Car> car = Optional.ofNullable(new Car("6", "ke4", "maruthi", "purple"));

		when(carRepository.findById(id, new PartitionKey(colour))).thenReturn(car);

		doNothing().when(carRepository).deleteById(id, new PartitionKey(colour));

		MvcResult mvcResult = mockMvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void CarNotFoundByID_partition_404() throws Exception {
		String id = "6";
		String colour = "skyblue";
		String url = burl + "getCar/" + id + "/colour/" + colour;

		mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("car not found with id " + id + "in the partion" + colour,
						result.getResolvedException().getMessage()));

	}

	@Test
	public void EmpltyInputException_404() throws Exception {
		Car car = new Car("1", "", "suzaki", "");
		String jsonCar = objectmapper.writeValueAsString(car);
		String url = burl + "saveCar";

		mockMvc.perform(post(url).content(jsonCar).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NullPointerException))
				.andExpect(result -> assertEquals("input fields canNot be empty",
						result.getResolvedException().getMessage()));
	}

}
