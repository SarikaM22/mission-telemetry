package com.telemetry.missiontelemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.telemetry")
@EnableScheduling
public class MissiontelemetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MissiontelemetryApplication.class, args);
	}

}
