# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
# Download dependencies for offline building
RUN ./mvnw dependency:go-offline
COPY src ./src
# Package the application
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["sh", "-c", "java -Dspring.data.mongodb.uri=mongodb+srv://vibecoding360_db_user:Kalel%40123@expense-tracker.o3hkity.mongodb.net/expense_tracker?appName=expense-tracker -jar app.jar"]
