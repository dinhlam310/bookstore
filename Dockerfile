#FROM ubuntu:latest AS build
#RUN apt-get update
#RUN apt-get install openjdk-20-jdk -y
#COPY . .
#RUN ./gradlew bootJar --no-daemon

#FROM openjdk:17-jdk-slim
#EXPOSE 8080
#COPY --from=build /build/libs/demo-1.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Sử dụng hình ảnh Docker chứa JDK
#FROM openjdk:11
# Tạo thư mục làm việc
#WORKDIR /app
# Sao chép tệp tin JAR đã được xây dựng vào container
#COPY target/myapp.jar /app/myapp.jar
## Chạy ứng dụng Spring Boot
#CMD ["java", "-jar", "/app/myapp.jar"]

#FROM openjdk:20
#EXPOSE 8081
#ADD target/spring-boot-docker.jar spring-boot-docker.jar
#ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]

## Sử dụng hình ảnh OpenJDK làm cơ sở
#FROM adoptopenjdk:20-jdk-hotspot
#
## Đặt thư mục làm việc
#WORKDIR /app
#
## Sao chép tất cả các tệp tin từ thư mục dự án vào thư mục làm việc trong image
#COPY . .
#
## Expose cổng 8080
#EXPOSE 8080
#
## Chạy ứng dụng Spring Boot
#ENTRYPOINT ["java", "-jar", "bookstore.jar"]

FROM openjdk:20
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8081

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/my_project
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=123456

# Thêm phụ thuộc MySQL JDBC driver
COPY mysql-connector-java.jar /mysql-connector-java.jar