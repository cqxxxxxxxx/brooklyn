#扩展原先的gitlab镜像
FROM gitlab/gitlab-runner:latest
WORKDIR /
ADD sources.list /etc/apt/sources.list
ADD kubectl  /bin/kubectl
ADD .kube /root/.kube
ADD .kube /home/gitlab-runner/.kube
RUN apt-get update \
&& apt-get install -y sudo \
&& echo 'gitlab-runner  ALL=NOPASSWD:ALL' >> /etc/sudoers \
&& apt-get install -y openjdk-8-jdk \
&& apt-get install -y maven \
&& apt-get install -y vim \
&& chmod 777 /bin/kubectl
