FROM centos:latest
LABEL maintainer="cqxxxxxxxx@gmail.com"

EXPOSE 9093/tcp
COPY ./alertmanager /alertmanager
VOLUME ["/alertmanager"]
WORKDIR /alertmanager
RUN chmod 777 alertmanager && ls -lh
ENTRYPOINT ["./alertmanager"]
CMD ["--config.file=./conf/alertmanager.yml"]