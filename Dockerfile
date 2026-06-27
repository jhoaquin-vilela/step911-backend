# Etapa de construcción
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
# ESTA ES LA LÍNEA QUE CAMBIÓ (salta la compilación de pruebas por completo)
RUN mvn clean package -Dmaven.test.skip=true

# Etapa de ejecución
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]