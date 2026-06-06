package com.telemetry.missiontelemetry;

import com.telemetry.model.TelemetryData;
import com.telemetry.service.TelemetrySimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MissiontelemetryApplicationTests {

	@Autowired
	private TelemetrySimulator telemetrySimulator;

	private TelemetryData telemetryData;

	@BeforeEach
	void setUp() {
		telemetryData = new TelemetryData();
	}

	@Test
	void contextLoads() {
		assertNotNull(telemetrySimulator, "Application context should load successfully");
	}

	@Test
	void testTelemetrySimulator() {
		telemetrySimulator.broadcastTelemetry();
		TelemetryData latest = telemetrySimulator.getLatestTelemetry();
		
		assertNotNull(latest, "Latest telemetry should not be null");
		assertNotNull(latest.getMissionId(), "Mission ID should be set");
		assertTrue(latest.getAltitude() >= 0, "Altitude should be non-negative");
		assertTrue(latest.getFuelLevel() >= 0 && latest.getFuelLevel() <= 100, "Fuel level should be 0-100");
	}

	@Test
	void testTelemetryDataModel() {
		telemetryData.setMissionId("TEST-MISSION-01");
		telemetryData.setAltitude(100.0);
		telemetryData.setVelocity(1000.0);
		telemetryData.setFuelLevel(75.5);
		telemetryData.setStatus("NOMINAL");

		assertEquals("TEST-MISSION-01", telemetryData.getMissionId());
		assertEquals(100.0, telemetryData.getAltitude());
		assertEquals(1000.0, telemetryData.getVelocity());
		assertEquals(75.5, telemetryData.getFuelLevel());
		assertEquals("NOMINAL", telemetryData.getStatus());
	}
}
