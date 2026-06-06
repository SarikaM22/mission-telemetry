# 🖥️ Command Reference - Copy & Paste Ready

## ⚡ Quick Commands (Copy-Paste Ready)

### 🚀 OPTION 1: Full Build & Run (RECOMMENDED)

**Step 1: Install React Dependencies**
```powershell
cd frontend
npm install
cd ..
```

**Step 2: Build Everything**
```powershell
.\mvnw.cmd clean install
```

**Step 3: Run the JAR**
```powershell
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

**Step 4: Open Browser**
```
http://localhost:8080/
```

✅ **You're done!** Dashboard should load.

---

### 👨‍💻 OPTION 2: Development Mode (Separate Backend & Frontend)

**Terminal 1 - Backend:**
```powershell
.\mvnw.cmd spring-boot:run
```

**Terminal 2 - Frontend (new terminal, same folder):**
```powershell
cd frontend
npm install
npm start
```

Opens: `http://localhost:3000`

---

### 🐳 OPTION 3: Docker (If Docker is Installed)

**Build & Run:**
```bash
docker-compose up
```

Then open: `http://localhost:8080/`

---

## 📋 Useful Commands

### Building

**Clean build (removes old artifacts):**
```bash
.\mvnw.cmd clean
```

**Build without tests (faster):**
```bash
.\mvnw.cmd clean install -DskipTests
```

**Build React only:**
```bash
cd frontend
npm run build
cd ..
```

**Build Backend only:**
```bash
.\mvnw.cmd clean install -pl "!frontend"
```

---

### Running

**Start backend server:**
```bash
.\mvnw.cmd spring-boot:run
```

**Start frontend dev server:**
```bash
cd frontend && npm start
```

**Run JAR file:**
```bash
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

**Run on different port:**
```bash
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar --server.port=8081
```

---

### Testing

**Run all tests:**
```bash
.\mvnw.cmd test
```

**Run tests without rebuilding:**
```bash
.\mvnw.cmd test -o
```

**Skip tests during build:**
```bash
.\mvnw.cmd install -DskipTests
```

**Run frontend tests:**
```bash
cd frontend && npm test
```

---

### API Testing

**Test health endpoint:**
```powershell
curl http://localhost:8080/api/telemetry/health
```

**Get latest telemetry:**
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/telemetry/latest" -Method Get | ConvertFrom-Json
```

**Pretty print JSON:**
```powershell
curl http://localhost:8080/api/telemetry/latest | jq
```

---

### Cleaning Up

**Remove build artifacts:**
```bash
.\mvnw.cmd clean
```

**Remove frontend node_modules:**
```bash
cd frontend
rm -r node_modules
npm install
cd ..
```

**Remove all generated files:**
```bash
.\mvnw.cmd clean
cd frontend && rm -r node_modules build
```

---

### Process Management

**Find process using port 8080:**
```powershell
Get-NetTCPConnection -LocalPort 8080 | Select-Object OwningProcess
```

**Kill process on port 8080:**
```powershell
Get-NetTCPConnection -LocalPort 8080 | Stop-Process -Force
```

**List running Java processes:**
```bash
jps -l
```

---

### Logging & Debugging

**View Maven debug output:**
```bash
.\mvnw.cmd clean install -X
```

**Run with verbose logging:**
```bash
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar --debug
```

**Check Spring logs:**
```bash
.\mvnw.cmd spring-boot:run --trace
```

---

### NPM Commands

**Install all dependencies:**
```bash
npm install
```

**Install specific package:**
```bash
npm install react@18.2.0
```

**Update all packages:**
```bash
npm update
```

**List installed packages:**
```bash
npm list
```

**Clear npm cache:**
```bash
npm cache clean --force
```

---

### Docker Commands

**Build Docker image:**
```bash
docker build -t mission-telemetry .
```

**Run Docker container:**
```bash
docker run -p 8080:8080 mission-telemetry
```

**View container logs:**
```bash
docker logs -f <container_id>
```

**Stop Docker container:**
```bash
docker stop <container_id>
```

**Remove Docker image:**
```bash
docker rmi mission-telemetry
```

---

## 🔧 Troubleshooting Commands

### Port Already in Use

**Windows:**
```powershell
# Find process using port 8080
Get-NetTCPConnection -LocalPort 8080 | Select-Object OwningProcess

# Kill process
Get-NetTCPConnection -LocalPort 8080 | Stop-Process -Force
```

**Mac/Linux:**
```bash
# Find process
lsof -i :8080

# Kill process
kill -9 <PID>
```

---

### Clear Everything & Start Fresh

```bash
# Full cleanup
.\mvnw.cmd clean
cd frontend
rm -r node_modules build
npm install
cd ..

# Rebuild
.\mvnw.cmd clean install

# Run
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

---

### Check Java & Node Versions

```bash
# Check Java version
java -version

# Check Node version
node -v

# Check npm version
npm -v

# Check Maven version
mvn -v
```

---

### View Project Info

**Maven:**
```bash
.\mvnw.cmd help:describe -Dplugin=org.springframework.boot:spring-boot-maven-plugin
```

**npm:**
```bash
cd frontend && npm info
```

---

## 📊 Inspection Commands

**View active connections:**
```powershell
netstat -ano | findstr :8080
```

**Check file structure:**
```bash
tree /F         # Windows
find . -type f  # Mac/Linux
```

**List Maven goals:**
```bash
.\mvnw.cmd help:goal
```

---

## 🚀 Complete Setup in One Block

Copy and run all at once:

```bash
# Clean everything
.\mvnw.cmd clean
cd frontend && rm -r node_modules 2>/dev/null; cd ..

# Install frontend deps
cd frontend
npm install
cd ..

# Build everything
.\mvnw.cmd clean install

# Run
java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar
```

Then open: `http://localhost:8080/`

---

## ⏱️ Estimated Times

| Command | Time |
|---------|------|
| `npm install` (first time) | 2-3 min |
| `mvn clean install` (first time) | 4-5 min |
| `mvn clean install` (cached) | 1-2 min |
| Backend startup | 2-3 sec |
| Frontend startup | 3-5 sec |
| Dashboard load | <1 sec |

---

## 🔍 Verification Commands

**Verify files created:**
```bash
# Check if frontend exists
dir frontend

# Check if JAR exists
dir target\*.jar

# Check if static files exist
dir src\main\resources\static
```

**Verify application running:**
```powershell
# Test health endpoint
curl http://localhost:8080/api/telemetry/health

# Test WebSocket endpoint
curl http://localhost:8080/ws-telemetry
```

---

## 📝 Common Issues & Fixes

| Issue | Command to Fix |
|-------|----------------|
| Port in use | `Get-NetTCPConnection -LocalPort 8080 \| Stop-Process -Force` |
| npm not found | Install Node.js from nodejs.org |
| Maven errors | `.\mvnw.cmd clean` then retry |
| React build fails | `cd frontend && rm -r node_modules && npm install` |

---

## 🎯 Most Used Commands (Quick Reference)

```bash
# Build & Run (One command)
.\mvnw.cmd clean install && java -jar target/missiontelemetry-0.0.1-SNAPSHOT.jar

# Development (Two terminals)
# Terminal 1: .\mvnw.cmd spring-boot:run
# Terminal 2: cd frontend && npm start

# Docker
docker-compose up

# Test APIs
curl http://localhost:8080/api/telemetry/health
curl http://localhost:8080/api/telemetry/latest
```

---

**All commands are copy-paste ready!** 🎉

Choose your option above and follow the steps. 🚀
