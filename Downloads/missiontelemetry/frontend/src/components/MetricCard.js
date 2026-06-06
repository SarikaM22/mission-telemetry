import React from 'react';
import './MetricCard.css';

export default function MetricCard({ label, value, unit, icon, progress }) {
  return (
    <div className="metric-card">
      <div className="metric-icon">{icon}</div>
      <h3>{label}</h3>
      <div className="metric-value">
        <span>{value}</span>
        <span className="unit">{unit}</span>
      </div>
      {progress !== undefined && (
        <div className="progress-bar">
          <div 
            className="progress-fill"
            style={{ 
              width: `${progress}%`,
              backgroundColor: progress > 30 ? '#00ff00' : progress > 10 ? '#ffff00' : '#ff0000'
            }}
          ></div>
        </div>
      )}
    </div>
  );
}
