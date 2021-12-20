#You can choose to build your api with java 8 or 11
#I've tested both using these images

#java 8
FROM openjdk:11
EXPOSE 8081
ADD build/libs/NewIMSE_Project-0.0.1-SNAPSHOT.jar NewIMSE_Project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "NewIMSE_Project-0.0.1-SNAPSHOT.jar"]

