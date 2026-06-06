package com.telemetry.controller;

import com.telemetry.model.TelemetryData;
import com.telemetry.service.TelemetrySimulator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetrySimulator telemetrySimulator;

    @GetMapping("/latest")
    public ResponseEntity<TelemetryData> getLatestTelemetry() {
        return ResponseEntity.ok(telemetrySimulator.getLatestTelemetry());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Telemetry service is running");
    }
}
