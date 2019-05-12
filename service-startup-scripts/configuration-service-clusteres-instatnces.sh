#!/bin/bash
gnome-terminal -- java -jar -Dspring.profiles.active=clustered-instance1 configuration-service-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar -Dspring.profiles.active=clustered-instance2 configuration-service-0.0.1-SNAPSHOT.jar
