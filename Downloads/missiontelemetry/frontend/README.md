# React Frontend - Mission Telemetry Dashboard

## Prerequisites
- Node.js 16+ and npm

## Installation

```bash
cd frontend
npm install
```

## Development

```bash
# Run in development mode (proxy to localhost:8080)
npm start
```

The app will open at `http://localhost:3000`

## Build for Production

```bash
# Build React app (creates build/ folder)
npm run build
```

The build folder contents will be copied to `src/main/resources/static/` by Maven during build.

## Project Structure

```
frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Dashboard.js
│   │   ├── Dashboard.css
│   │   ├── MetricCard.js
│   │   ├── MetricCard.css
│   │   ├── PhaseTimeline.js
│   │   ├── PhaseTimeline.css
│   │   ├── StatusBanner.js
│   │   └── StatusBanner.css
│   ├── App.js
│   ├── index.js
│   └── index.css
└── package.json
```

## Features

- Real-time telemetry updates via WebSocket (STOMP)
- Responsive metrics display
- Mission phase timeline
- Connection status indicator
- Live timestamp updates
- Smooth animations and transitions
- Mobile-friendly design
