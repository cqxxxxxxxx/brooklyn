FROM centos:latest
LABEL maintainer="cqxxxxxxxx@gmail.com"

EXPOSE 9090/tcp
COPY ./prometheus /prometheus
VOLUME ["/prometheus"]
WORKDIR /prometheus
RUN chmod 777 prometheus && ls -lh
ENTRYPOINT ["./prometheus"]
CMD ["--config.file=./conf/prometheus.yml", "--storage.tsdb.path=./data", "--web.console.libraries=./console_libraries", "--web.console.templates=./consoles"]