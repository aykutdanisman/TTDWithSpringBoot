package com.kutztech.demo.CarExm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CarRepositoryTest {
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testGetCarByName() {
		
		entityManager.persistAndFlush(new Car("pirus","hybrid"));
		
		Car car = carRepo.findByName("pirus");
		
		assertThat(car.getName()).isEqualTo("pirus");
		assertThat(car.getType()).isEqualTo("hybrid");
		
	}

}
