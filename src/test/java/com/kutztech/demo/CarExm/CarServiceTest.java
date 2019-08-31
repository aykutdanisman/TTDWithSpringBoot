package com.kutztech.demo.CarExm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)// does not use spring context. With this runner @MockBean wont work. Tests will run faster
public class CarServiceTest {
	
	@Mock
	private CarRepository carRepo;
	
	private CarService carServ;
	
	@Before
	public void setUp() {
		carServ = new CarService(carRepo);
	}

	@Test(expected = CarNotFoundException.class)//we are expecting service throws an exception when repository returns null to it. 
	public void test_getCarDetails_noDataFound() throws Exception{
		given(carRepo.findByName("pirus")).willReturn(null);
		
		carServ.getCarDetails("pirus");
	}
	
	@Test()
	public void test_getCarDetails_oneDataFound() throws Exception{
		given(carRepo.findByName("pirus")).willReturn(new Car("pirus", "hybrid"));
		
		Car car = carServ.getCarDetails("pirus");
		
		assertThat(car.getName()).isEqualTo("pirus");
		assertThat(car.getType()).isEqualTo("hybrid");
	}

}
