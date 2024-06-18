FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /bachhoasi
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk AS deploy
WORKDIR /bachhoasi
COPY --from=builder /bachhoasi/target/bachhoasi-0.0.1-SNAPSHOT.jar /bachhoasi/bachhoasi.jar
ENTRYPOINT ["java","-jar","bachhoasi.jar"]
