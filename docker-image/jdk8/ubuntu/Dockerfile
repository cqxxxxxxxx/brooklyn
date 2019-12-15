FROM ubuntu:latest
LABEL maintainer="cqxxxxxxxx@gmail.com"

WORKDIR /data
COPY ./jre1.8.0_231 ./jre1.8.0_231
ENV JAVA_HOME /data/jre1.8.0_231
ENV PATH $PATH:$JAVA_HOME/bin
CMD ["java", "-version"]