FROM openjdk:17

ADD target/FXDeals.jar FXDeals.jar

CMD ["java", "-jar", "FXDeals.jar"]