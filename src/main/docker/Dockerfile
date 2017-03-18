FROM java:8
VOLUME /tmp
ADD springmodule-0.0.1-SNAPSHOT.jar springmodule.jar
RUN sh -c 'touch /springmodule.jar'
EXPOSE 8090
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springmodule.jar" ]
