FROM java:8
EXPOSE 8090
ADD gs-spring-boot-docker-0.1.0.jar springmodule.jar
RUN sh -c 'touch /springmodule.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springmodule.jar" ]