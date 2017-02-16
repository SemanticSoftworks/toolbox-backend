FROM java:8
ADD springmodule-0.0.1-SNAPSHOT.jar springmodule.jar
RUN sh -c 'touch /springmodule.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springmodule.jar --spring.config.location=/home/docker/Downloads/application.properties" ]
