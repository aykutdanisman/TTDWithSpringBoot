package com.kutztech.demo.CarExm;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CarService carService;
	
	/**
	 * This test does not start a web server, instead MockMvc is used to test the controller.
	 * @throws Exception
	 */
	
	@Test
	public void test_getCar_DataFound() throws Exception{
		
		given(carService.getCarDetails(anyString())).willReturn(new Car("pirus","hybrid"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cars/pirus")).andExpect(status().isOk())
		.andExpect(jsonPath("name").value("pirus"))
		.andExpect(jsonPath("type").value("hybrid"));
		
	}
	
	@Test
	public void test_getCar_NoDataFound() throws Exception{
		
		given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cars/pirus"))
		.andExpect(status().isNotFound());
	
	}
	
	
	
	
	//Some more examples on mockMvc
	/*
	 * .andExpect(flash().attributeCount(1)) .andExpect(flash().attribute("message",
	 * "success!")) .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	 * .andExpect(jsonPath("$.person.name").value("Jason"));
	 * redirectedUrl("/person/1"), model().size(1),
	 * model().attributeExists("person"), flash().attributeCount(1),
	 * flash().attribute("message", "success!"))
	 */


}
