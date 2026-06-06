package com.telemetry.model;

import lombok.Data;
import java.time.Instant;

@Data
public class TelemetryData {
    private String missionId;
    private Instant timestamp;
    private double altitude;
    private double velocity;
    private double fuelLevel;
    private double temperature;
    private double signalStrength;
    private double acceleration;
    private String missionPhase;
    private String status;
}
