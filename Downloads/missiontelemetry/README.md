# Mission Telemetry Service

A **real-time spacecraft telemetry simulation** and broadcasting service built with **Spring Boot** backend and **React** frontend.

## 🎯 Features

✅ **Spring Boot Backend**
- Real-time telemetry streaming via WebSocket (STOMP)
- REST API endpoints
- CORS enabled for cross-origin requests
- Mission phase simulation (LAUNCH → ASCENT → SEPARATION → ORBIT)

✅ **React Frontend Dashboard**
- Live telemetry data visualization
- Real-time metric updates
- Mission phase timeline
- Connection status indicator
- Responsive mobile-friendly design
- Smooth animations and transitions

✅ **Telemetry Metrics**
- Altitude tracking
- Velocity measurement
- Fuel level monitoring
- Temperature reading
- Signal strength
- Acceleration data

## 🛠️ Technology Stack

**Backend:**
- Java 17
- Spring Boot 3.5.15
- WebSocket with STOMP
- Maven 3.x
- Lombok

**Frontend:**
- React 18
- SockJS for WebSocket
- CSS3 with animations
- Responsive design

## 📋 Prerequisites

- **Java 17+** - For running Spring Boot
- **Node.js 16+** and **npm** - For React development
- **Maven 3.8+** - For building the project

## 🚀 Quick Start

### Option 1: Full Build (Backend + Frontend Combined)

**1. Install Node dependencies:**
```bash
cd frontend
npm install
cd ..
```

**2. Build with Maven:**
```bash
.\mvnw.cmd clean install
```

This will:
- Build the React frontend
- Copy build files to `src/main/resources/static/`
- Package the entire application

**3. Run the JAR:**
```bash
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

**4. Open browser:**
```
http://localhost:8080/
```

---

### Option 2: Development Setup (Separate Frontend/Backend)

**Terminal 1 - Backend:**
```bash
.\mvnw.cmd spring-boot:run
```
Backend runs on `http://localhost:8080`

**Terminal 2 - Frontend (in separate folder):**
```bash
cd frontend
npm install
npm start
```
Frontend runs on `http://localhost:3000` (proxies to backend on 8080)

---

## 📡 API Endpoints

### REST API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/telemetry/health` | Health check |
| GET | `/api/telemetry/latest` | Get latest telemetry data |

**Example:**
```bash
curl http://localhost:8080/api/telemetry/health
curl http://localhost:8080/api/telemetry/latest
```

### WebSocket

**Endpoint:** `ws://localhost:8080/ws-telemetry`  
**Subscription Topic:** `/topic/telemetry`  
**Update Frequency:** 1 second

**Example (JavaScript):**
```javascript
const socket = new SockJS('/ws-telemetry');
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
  stompClient.subscribe('/topic/telemetry', (message) => {
    console.log('Telemetry:', JSON.parse(message.body));
  });
});
```

---

## 📊 Telemetry Data Structure

```json
{
  "missionId": "MISSION-ISRO-42",
  "timestamp": "2026-06-06T20:35:12.456Z",
  "altitude": 125.43,
  "velocity": 2450.78,
  "fuelLevel": 68.92,
  "temperature": -35.67,
  "signalStrength": -92.34,
  "acceleration": 8.95,
  "missionPhase": "ASCENT",
  "status": "NOMINAL"
}
```

### Field Details

| Field | Type | Unit | Range | Description |
|-------|------|------|-------|-------------|
| `missionId` | String | - | - | Mission identifier |
| `timestamp` | ISO 8601 | - | - | Update timestamp |
| `altitude` | Double | km | 0-400 | Height above ground |
| `velocity` | Double | m/s | 0-7800 | Speed of spacecraft |
| `fuelLevel` | Double | % | 0-100 | Remaining fuel |
| `temperature` | Double | °C | -80-20 | External temperature |
| `signalStrength` | Double | dBm | -120 to -60 | Signal quality |
| `acceleration` | Double | m/s² | 0-15 | Current acceleration |
| `missionPhase` | String | - | LAUNCH/ASCENT/SEPARATION/ORBIT | Current phase |
| `status` | String | - | NOMINAL/WARNING/CRITICAL | System status |

---

## 📂 Project Structure

```
missiontelemetry/
├── src/
│   ├── main/
│   │   ├── java/com/telemetry/
│   │   │   ├── config/
│   │   │   │   ├── CorsConfig.java
│   │   │   │   └── WebSocketConfig.java
│   │   │   ├── controller/
│   │   │   │   └── TelemetryController.java
│   │   │   ├── model/
│   │   │   │   └── TelemetryData.java
│   │   │   ├── service/
│   │   │   │   └── TelemetrySimulator.java
│   │   │   └── missiontelemetry/
│   │   │       └── MissiontelemetryApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/ (React build output)
│   └── test/
│       └── java/
│           └── MissiontelemetryApplicationTests.java
├── frontend/
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/
│   │   │   ├── Dashboard.js
│   │   │   ├── MetricCard.js
│   │   │   ├── PhaseTimeline.js
│   │   │   └── StatusBanner.js
│   │   ├── App.js
│   │   └── index.js
│   └── package.json
├── pom.xml
└── README.md
```

---

## 🎨 React Components

### Dashboard
Main component that manages WebSocket connection and telemetry state.

### MetricCard
Displays individual metrics (altitude, velocity, fuel, etc.) with optional progress bar.

### PhaseTimeline
Shows mission phases with visual indicators and animations.

### StatusBanner
Displays connection status and error messages.

---

## 🔄 Mission Phases

The simulator progresses through mission phases:

| Phase | Duration | Description | Altitude | Velocity |
|-------|----------|-------------|----------|----------|
| **LAUNCH** | 0-30 sec | Initial acceleration | 0-1.95 km | 0-1950 m/s |
| **ASCENT** | 30-120 sec | Rapid altitude gain | 1.95-55 km | 1950-7800 m/s |
| **SEPARATION** | 120-200 sec | Stage separation | 55-390 km | 7800-6600 m/s |
| **ORBIT** | 200+ sec | Orbital insertion | 390-400 km | 6600-6800 m/s |

---

## 🧪 Running Tests

```bash
.\mvnw.cmd test
```

Tests validate:
- Application context loading
- Telemetry simulator functionality
- Data model validation
- WebSocket connectivity

---

## 🐳 Docker (Optional)

Build Docker image:
```bash
docker build -t mission-telemetry .
```

Run container:
```bash
docker run -p 8080:8080 mission-telemetry
```

---

## 🔧 Development Commands

**Backend only:**
```bash
.\mvnw.cmd spring-boot:run
```

**Frontend only:**
```bash
cd frontend
npm start
```

**Full build:**
```bash
.\mvnw.cmd clean install
```

**Run JAR:**
```bash
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

**Clean build:**
```bash
.\mvnw.cmd clean
```

---

## ⚠️ Troubleshooting

| Issue | Solution |
|-------|----------|
| Port 8080 in use | Kill process: `Get-NetTCPConnection -LocalPort 8080 \| Stop-Process -Force` |
| npm not found | Install Node.js from https://nodejs.org |
| Maven not found | Use `.\mvnw.cmd` instead of `mvn` |
| WebSocket fails | Check CORS config and ensure backend is running |
| React build fails | Delete `frontend/node_modules` and run `npm install` again |

---

## 📝 Configuration

**Server Port** - Edit `src/main/resources/application.properties`:
```properties
server.port=8080
server.servlet.context-path=/
```

**WebSocket Settings** - Edit `src/main/java/com/telemetry/config/WebSocketConfig.java`

**CORS Settings** - Edit `src/main/java/com/telemetry/config/CorsConfig.java`

---

## 🚀 Production Deployment

1. **Build with Maven:**
   ```bash
   .\mvnw.cmd clean install -DskipTests
   ```

2. **Run JAR:**
   ```bash
   java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
   ```

3. **Access:**
   ```
   http://your-server:8080/
   ```

---

## 📖 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [WebSocket STOMP](https://spring.io/guides/gs/messaging-stomp-websocket/)
- [Maven Documentation](https://maven.apache.org)

---

## ✨ Recent Changes

✅ **React Frontend Dashboard** - Modern UI with real-time updates  
✅ **Maven Frontend Plugin** - Automated React build during Maven build  
✅ **Package Scanning** - Fixed component discovery  
✅ **REST API** - Health check and latest telemetry endpoints  
✅ **Comprehensive Tests** - Unit tests with proper setup  
✅ **Documentation** - Complete setup and API documentation  

---

## 🎯 Future Enhancements

- [ ] Database persistence (mission history)
- [ ] Multiple mission simulations
- [ ] Advanced analytics dashboard
- [ ] Real-time notifications
- [ ] Data export (CSV/JSON)
- [ ] User authentication
- [ ] Deployment guides (Docker/Kubernetes)

---

Ready to launch? 🚀 Follow the **Quick Start** section above!
```

## Building & Running

### Build
```bash
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

Or:
```bash
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

The service will start on `http://localhost:8080`

## WebSocket Client Example

```javascript
const socket = new SockJS('http://localhost:8080/ws-telemetry');
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame);
    
    stompClient.subscribe('/topic/telemetry', (message) => {
        const telemetry = JSON.parse(message.body);
        console.log('Telemetry Update:', telemetry);
    });
});
```

## Mission Phases

The simulator progresses through mission phases based on elapsed time:

- **LAUNCH** (0-30 sec): Initial acceleration phase
- **ASCENT** (30-120 sec): Rapid altitude gain, max velocity buildup
- **SEPARATION** (120-200 sec): Stage separation, deceleration begins
- **ORBIT** (200+ sec): Orbital insertion and stabilization

## Recent Changes

✨ Added REST controller for HTTP telemetry access  
✨ Added health check endpoint  
✨ Enhanced logging configuration  
✨ Added `@EnableScheduling` to main application class  
✨ Store latest telemetry state for REST queries  
✨ Updated POM.xml with project metadata  

## Future Enhancements

- [ ] Database persistence for historical telemetry
- [ ] Anomaly detection and alerts
- [ ] Mission control dashboard
- [ ] Multiple simultaneous mission simulations
- [ ] Telemetry data export (CSV/JSON)
- [ ] Docker containerization
