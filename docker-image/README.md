## 自己写的常用镜像

#### jdk8
基于centos制作出来的镜像450MB  
基于ubuntu制作出来的镜像300MB

#### prometheus && alertmanager镜像
执行build.sh构建镜像  
执行docker-compose up -d 启动  
配置文件统一通过conf目录挂载到容器中  