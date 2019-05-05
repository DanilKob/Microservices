#!/bin/bash
gnome-terminal -- java -jar -Dspring.profiles.active=instance1 simple-eureka-client-service-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar -Dspring.profiles.active=instance2 simple-eureka-client-service-0.0.1-SNAPSHOT.jar