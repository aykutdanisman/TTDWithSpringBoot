package com.kutztech.demo.CarExm;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationCarController {
	
	@Autowired
	private TestRestTemplate restTemp;

	@Test
	public void test() {
		ResponseEntity<Car> resp = restTemp.getForEntity("/cars/pirus", Car.class);
		Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(resp.getBody().getName()).isEqualTo("pirus");
		Assertions.assertThat(resp.getBody().getType()).isEqualTo("hybrid");
	}

}
