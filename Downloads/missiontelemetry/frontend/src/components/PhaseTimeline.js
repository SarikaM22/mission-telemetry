import React from 'react';
import './PhaseTimeline.css';

export default function PhaseTimeline({ currentPhase }) {
  const phases = [
    { name: 'LAUNCH', icon: '🔥', description: '0-30s' },
    { name: 'ASCENT', icon: '📈', description: '30-120s' },
    { name: 'SEPARATION', icon: '⚡', description: '120-200s' },
    { name: 'ORBIT', icon: '🌍', description: '200+s' }
  ];

  return (
    <div className="phase-timeline">
      <h2>Mission Phases</h2>
      <div className="phases-container">
        {phases.map((phase, index) => (
          <div
            key={phase.name}
            className={`phase ${currentPhase === phase.name ? 'active' : ''}`}
          >
            <div className="phase-icon">{phase.icon}</div>
            <div className="phase-name">{phase.name}</div>
            <div className="phase-time">{phase.description}</div>
            {index < phases.length - 1 && <div className="phase-connector"></div>}
          </div>
        ))}
      </div>
    </div>
  );
}
