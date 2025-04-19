FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY wait-for-it.sh ./wait-for-it.sh

RUN chmod +x ./wait-for-it.sh

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/wait-for-it.sh /app/wait-for-it.sh
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["/app/wait-for-it.sh", "auto_parts_shop-db:3306", "--", "java", "-jar", "/app/app.jar"]
