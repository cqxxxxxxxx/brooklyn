FROM java:alpine
VOLUME /tmp
WORKDIR /
ADD *.jar app.jar
ADD docker-entrypoint.sh docker-entrypoint.sh
ENTRYPOINT ["sh", "docker-entrypoint.sh"]