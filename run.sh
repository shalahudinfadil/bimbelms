#!/bin/bash

#while inotifywait -r -e modify,create,delete,move /app/src/main/; do mvn compile -o -DskipTests; done >/dev/null 2>&1 &
#mvn spring-boot:run

#spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" &
#while true; do
#  inotifywait -e modify,create,delete,move -r ./src/ && ./mvnw compile
#done

export TERM=xterm

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" &

while true; do
    watch -d -t -g "ls -lR . | sha1sum" && mvn compile
done