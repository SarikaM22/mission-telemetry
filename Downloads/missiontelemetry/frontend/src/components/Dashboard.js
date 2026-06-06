import React, { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import './Dashboard.css';
import MetricCard from './MetricCard';
import PhaseTimeline from './PhaseTimeline';
import StatusBanner from './StatusBanner';

export default function Dashboard() {
  const [telemetry, setTelemetry] = useState({
    missionId: '-',
    altitude: 0,
    velocity: 0,
    fuelLevel: 0,
    temperature: 0,
    signalStrength: 0,
    acceleration: 0,
    missionPhase: 'LAUNCH',
    status: 'NOMINAL',
    timestamp: new Date().toISOString()
  });
  
  const [connected, setConnected] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const client = new Client({
      brokerURL: `ws://${window.location.hostname}:${window.location.port}/ws-telemetry`,
      reconnectDelay: 3000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    client.onConnect = () => {
      setConnected(true);
      setError(null);
      
      client.subscribe('/topic/telemetry', (message) => {
        try {
          const data = JSON.parse(message.body);
          setTelemetry(data);
        } catch (e) {
          console.error('Failed to parse telemetry data:', e);
        }
      });
    };

    client.onStompError = (frame) => {
      setConnected(false);
      setError('WebSocket connection failed. Retrying...');
      console.error('STOMP error:', frame);
    };

    client.onDisconnect = () => {
      setConnected(false);
    };

    client.activate();

    return () => {
      if (client.active) {
        client.deactivate();
      }
    };
  }, []);

  const getStatusColor = (status) => {
    switch (status) {
      case 'NOMINAL':
        return '#00ff00';
      case 'WARNING':
        return '#ffff00';
      case 'CRITICAL':
        return '#ff0000';
      default:
        return '#aaa';
    }
  };

  return (
    <div className="dashboard">
      <header className="dashboard-header">
        <h1>🚀 Mission Telemetry Dashboard</h1>
        <div className={`connection-status ${connected ? 'connected' : 'disconnected'}`}>
          <span className="status-dot"></span>
          {connected ? 'Connected' : 'Connecting...'}
        </div>
      </header>

      {error && <StatusBanner message={error} type="error" />}

      <div className="mission-info">
        <div className="info-row">
          <label>Mission ID:</label>
          <span>{telemetry.missionId}</span>
        </div>
        <div className="info-row">
          <label>Current Phase:</label>
          <span className="phase-badge">{telemetry.missionPhase}</span>
        </div>
        <div className="info-row">
          <label>Status:</label>
          <span className="status-badge" style={{ backgroundColor: getStatusColor(telemetry.status) }}>
            {telemetry.status}
          </span>
        </div>
      </div>

      <div className="metrics-grid">
        <MetricCard
          label="Altitude"
          value={telemetry.altitude.toFixed(2)}
          unit="km"
          icon="📍"
        />
        <MetricCard
          label="Velocity"
          value={telemetry.velocity.toFixed(2)}
          unit="m/s"
          icon="⚡"
        />
        <MetricCard
          label="Fuel Level"
          value={telemetry.fuelLevel.toFixed(2)}
          unit="%"
          icon="⛽"
          progress={telemetry.fuelLevel}
        />
        <MetricCard
          label="Temperature"
          value={telemetry.temperature.toFixed(2)}
          unit="°C"
          icon="🌡️"
        />
        <MetricCard
          label="Signal Strength"
          value={telemetry.signalStrength.toFixed(2)}
          unit="dBm"
          icon="📡"
        />
        <MetricCard
          label="Acceleration"
          value={telemetry.acceleration.toFixed(2)}
          unit="m/s²"
          icon="🔋"
        />
      </div>

      <PhaseTimeline currentPhase={telemetry.missionPhase} />

      <div className="timestamp">
        Last Updated: {new Date(telemetry.timestamp).toLocaleTimeString()}
      </div>
    </div>
  );
}
