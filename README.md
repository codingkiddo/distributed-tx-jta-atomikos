# Distributed Transaction Demo (JTA with Atomikos)

Demonstrates a single global transaction (XA / 2PC) across **two Postgres databases** using **JTA + Atomikos** in Spring Boot 3.

## Run
```bash
docker compose up -d
./mvnw spring-boot:run
# Or: mvn -q -DskipTests package && java -jar target/*SNAPSHOT.jar
```

Defaults (override with env vars):
- DB1_URL=jdbc:postgresql://localhost:5433/app  (user/pass: app/app)
- DB2_URL=jdbc:postgresql://localhost:5434/audit (user/pass: audit/audit)

## Test
```bash
curl -X POST http://localhost:8080/api/transfer   -H 'Content-Type: application/json'   -d '{"from":"A-100","to":"A-200","amount": 123.45}'
```

Uncomment the simulated exception in `TransferService` to observe 2PC rollback across both DBs.
