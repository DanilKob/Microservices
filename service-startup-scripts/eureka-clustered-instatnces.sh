#!/bin/bash
gnome-terminal -- java -jar -Dspring.profiles.active=wind eureka-service-register-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar -Dspring.profiles.active=water eureka-service-register-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar -Dspring.profiles.active=fire eureka-service-register-0.0.1-SNAPSHOT.jar