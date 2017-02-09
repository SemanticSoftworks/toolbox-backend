FROM java:8
EXPOSE 8090
ADD /springmodule-0.0.1-SNAPSHOT.jar springmodule.jar
RUN sh -c 'touch /springmodule-0.0.1-SNAPSHOT.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springmodule-0.0.1-SNAPSHOT.jar" ]
