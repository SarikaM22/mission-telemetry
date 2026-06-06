# 🚀 Quick Start Guide - Mission Telemetry Service

## ⚡ 5-Minute Setup

### Prerequisites Check
- ✅ Java 17+ installed? Check: `java -version`
- ✅ Node.js 16+ installed? Check: `node -v` and `npm -v`
- ✅ Maven installed? (bundled with project as `mvnw.cmd`)

---

## 🎯 Fastest Way to Run (Option 1)

### Step 1: Install React Dependencies
```powershell
cd frontend
npm install
cd ..
```
⏱️ **Time: ~2 minutes** (downloads npm packages)

### Step 2: Build Everything
```powershell
.\mvnw.cmd clean install
```
⏱️ **Time: ~3-4 minutes** (builds React + Backend + Tests)

### Step 3: Start the Application
```powershell
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```
⏱️ **Time: ~2 seconds** (instant startup)

### Step 4: Open in Browser
```
http://localhost:8080/
```

✅ **You should see the dashboard!**

---

## 👨‍💻 Development Mode (Option 2)

**Better for active development** (faster hot-reload)

### Terminal 1: Start Backend
```powershell
.\mvnw.cmd spring-boot:run
```

### Terminal 2: Start Frontend
```powershell
cd frontend
npm install
npm start
```

Opens: http://localhost:3000 (frontend with backend proxy)

---

## 📋 What You'll See

### Dashboard Features
✅ **Real-time Telemetry** - Updates every 1 second  
✅ **6 Metrics** - Altitude, Velocity, Fuel, Temperature, Signal, Acceleration  
✅ **Mission Phases** - LAUNCH → ASCENT → SEPARATION → ORBIT  
✅ **Connection Status** - Green dot when connected  
✅ **Live Timestamp** - Shows last update time  

### Example Metrics
```
Altitude:        156.78 km
Velocity:       3200.45 m/s
Fuel Level:      52.34 %
Temperature:    -48.56 °C
Signal:         -98.23 dBm
Acceleration:     7.45 m/s²
```

---

## 🧪 Test the APIs

Open **PowerShell** and test:

```powershell
# Health check
curl http://localhost:8080/api/telemetry/health

# Get latest telemetry
curl http://localhost:8080/api/telemetry/latest
```

---

## ⚠️ Troubleshooting

| Problem | Solution |
|---------|----------|
| **Port 8080 in use** | `Get-NetTCPConnection -LocalPort 8080 \| Stop-Process -Force` |
| **npm not found** | Install Node.js from https://nodejs.org |
| **Build fails** | Delete `frontend/node_modules` and `target/`, then retry |
| **Dashboard not loading** | Check browser console (F12) for errors |
| **No telemetry updates** | Check if backend is running (look for "Tomcat started on port 8080") |

---

## 📂 Important Files

| File | Purpose |
|------|---------|
| `pom.xml` | Maven build configuration |
| `frontend/package.json` | React dependencies |
| `src/main/java/com/telemetry/missiontelemetry/MissiontelemetryApplication.java` | Backend entry point |
| `frontend/src/components/Dashboard.js` | React dashboard component |

---

## 🔗 API Endpoints

**REST:**
- `GET /api/telemetry/health` - Health check
- `GET /api/telemetry/latest` - Latest telemetry data

**WebSocket:**
- `ws://localhost:8080/ws-telemetry` - Real-time stream
- Subscribe to: `/topic/telemetry`

---

## 📊 Full Build Process

When you run `.\mvnw.cmd clean install`, this happens:

1. ✅ NPM installs dependencies
2. ✅ React builds the app (optimized)
3. ✅ Copies React build to backend
4. ✅ Compiles Java code
5. ✅ Runs tests
6. ✅ Packages JAR file

**Result:** `target/missiontelemetry-0.0.1-SNAPSHOT.jar`

---

## 📖 Documentation

- **Full Setup:** See `README.md`
- **All Changes:** See `CHANGES.md`
- **Frontend Docs:** See `frontend/README.md`

---

## 🎯 Next Steps

After running the application:

1. ✅ Verify dashboard loads and shows data
2. ✅ Check that metrics update every second
3. ✅ Watch mission progress through phases
4. ✅ Monitor fuel level decreasing
5. ✅ Review the code in `frontend/src/components/`

---

## 💡 Tips

- **Development:** Use Option 2 (npm start) for hot-reload
- **Production:** Use Option 1 (JAR file) for deployment
- **Debugging:** Open browser DevTools (F12) to see console logs
- **Logs:** Backend logs appear in the terminal where you started it

---

## ✅ Verification

After opening dashboard, you should see:

```
✅ "🟢 Connected" in top-right corner
✅ Mission ID: MISSION-ISRO-42
✅ Metrics updating with decimals
✅ Fuel level showing progress bar
✅ Phase highlighted in timeline
✅ Timestamp updating every second
```

---

## 🚀 Ready to Launch!

Choose your option above and follow the steps. You should have the dashboard running in **~10 minutes total**!

Questions? Check `README.md` or `CHANGES.md` for detailed information.

Happy telemetry! 🌌
