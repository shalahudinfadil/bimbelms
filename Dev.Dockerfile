# Build Part
#FROM maven:3.9-eclipse-temurin-21-jammy as build
#WORKDIR /build
#
#COPY pom.xml /build
#
#RUN mvn go-offline:resolve-dependencies
#
#COPY src /build/src
#
#RUN mvn clean package -Dmaven.test.skip
#
## Runtime Part
#FROM eclipse-temurin:21-jre-jammy as runtime
#
#RUN apt-get update && apt-get -y upgrade
#RUN apt-get install -y inotify-tools dos2unix
#
#EXPOSE 8001
#
#ENV APP_HOME /app
#ENV JAVA_OPTS=""
#
##Create base app folder
#RUN mkdir $APP_HOME
##Create folder to save configuration files
#RUN mkdir $APP_HOME/config
##Create folder with application logs
#RUN mkdir $APP_HOME/log
#
#VOLUME $APP_HOME/log
#VOLUME $APP_HOME/config
#
#WORKDIR $APP_HOME
#COPY --from=deps /root/.m2/repository /root/.m2/repository
##Copy executable jar file from the builder image
#COPY --from=build /build/target/*.jar $APP_HOME/app.jar
#
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME/app.jar" ]

FROM maven:3-eclipse-temurin-21-jammy AS deps

WORKDIR /app
COPY pom.xml /app

RUN mvn go-offline:resolve-dependencies


FROM maven:3-eclipse-temurin-21-jammy AS build

WORKDIR /app
COPY --from=deps /root/.m2/repository /root/.m2/repository
COPY . /app

RUN mvn package -DskipTests -o


FROM maven:3-eclipse-temurin-21-alpine AS dev

WORKDIR /app
COPY --from=deps /root/.m2/repository /root/.m2/repository
COPY ./run.sh /run.sh

RUN chmod +x /run.sh

ENTRYPOINT ["/run.sh"]
