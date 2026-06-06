package com.telemetry.service;

import com.telemetry.model.TelemetryData;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TelemetrySimulator {

    private final SimpMessagingTemplate messagingTemplate;
    private final Random random = new Random();

    private double altitude = 0;
    private double velocity = 0;
    private double fuel = 100.0;
    private int tick = 0;
    private TelemetryData latestTelemetry;

    @Scheduled(fixedRate = 1000)
    public void broadcastTelemetry() {
        tick++;
        TelemetryData data = generateTelemetry();
        this.latestTelemetry = data;
        messagingTemplate.convertAndSend("/topic/telemetry", data);
    }

    public TelemetryData getLatestTelemetry() {
        return latestTelemetry != null ? latestTelemetry : generateTelemetry();
    }

    private TelemetryData generateTelemetry() {
        altitude = Math.min(400, altitude + (velocity / 1000.0));
        velocity = Math.min(7800, velocity + (tick < 120 ? 65 : -2) + (random.nextDouble() - 0.5) * 10);
        fuel = Math.max(0, fuel - (tick < 120 ? 0.3 : 0.05));

        double temp = 20 + (altitude > 100 ? -80 + random.nextDouble() * 20 : altitude * 0.5);
        double signal = Math.max(-120, -60 - (altitude * 0.1) + (random.nextDouble() - 0.5) * 5);
        double acceleration = tick < 120 ? 9.8 + random.nextDouble() * 5 : random.nextDouble() * 0.5;

        String phase = tick < 30 ? "LAUNCH" : tick < 120 ? "ASCENT" : tick < 200 ? "SEPARATION" : "ORBIT";
        String status = fuel < 10 ? "CRITICAL" : fuel < 30 ? "WARNING" : "NOMINAL";

        TelemetryData d = new TelemetryData();
        d.setMissionId("MISSION-ISRO-42");
        d.setTimestamp(Instant.now());
        d.setAltitude(Math.round(altitude * 100.0) / 100.0);
        d.setVelocity(Math.round(velocity * 100.0) / 100.0);
        d.setFuelLevel(Math.round(fuel * 100.0) / 100.0);
        d.setTemperature(Math.round(temp * 100.0) / 100.0);
        d.setSignalStrength(Math.round(signal * 100.0) / 100.0);
        d.setAcceleration(Math.round(acceleration * 100.0) / 100.0);
        d.setMissionPhase(phase);
        d.setStatus(status);
        return d;
    }
}
