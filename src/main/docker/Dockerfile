FROM openjdk

VOLUME /tmp
ADD maven/discs-0.0.1-SNAPSHOT.jar discs.jar
RUN sh -c 'touch /discs.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/discs.jar"]
