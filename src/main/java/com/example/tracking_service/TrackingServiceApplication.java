package com.example.tracking_service;

import com.example.tracking_service.model.Tracking;
import com.example.tracking_service.repository.TrackingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TrackingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(TrackingRepository trackingRepository) {
		return args -> {
			//Create dummy Tracking data
			Tracking tracking1 = new Tracking();
			tracking1.setOrderId(1);
			tracking1.setItemId(2);
			tracking1.setStatus("shipped");
			tracking1.setDate(LocalDate.parse("2023-03-28"));

			trackingRepository.save(tracking1);

		};
	}
}
