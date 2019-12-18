FROM openjdk:12 

COPY target/catalogue-course-1.0.0.jar catalogue-course-1.0.0.jar

EXPOSE 8080
 
CMD ["java", "-jar", "-Dserver.port=8080", "catalogue-course-1.0.0.jar"]
