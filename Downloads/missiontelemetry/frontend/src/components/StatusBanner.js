import React from 'react';
import './StatusBanner.css';

export default function StatusBanner({ message, type = 'info' }) {
  return (
    <div className={`status-banner status-${type}`}>
      {type === 'error' && '❌ '}
      {type === 'success' && '✅ '}
      {type === 'warning' && '⚠️ '}
      {message}
    </div>
  );
}
