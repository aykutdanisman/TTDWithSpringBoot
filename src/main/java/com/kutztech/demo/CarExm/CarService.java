package com.kutztech.demo.CarExm;

import org.springframework.stereotype.Service;

@Service
public class CarService {

	private CarRepository carRepo;

	public CarService(CarRepository carRepo) {
		this.carRepo = carRepo;
	}

	public Car getCarDetails(String name) {
		Car car =  carRepo.findByName(name);
		if(car == null)
			throw new CarNotFoundException();
		return car;
	}

}
