### gitlab-runner镜像
1. 配置了阿里apt源
2. 安装了jdk8
3. 安装了maven
4. 安装了kubectl, .kube配置文件需要自己设置
5. 安装了sudo, 并给gitlab-runner用户配置了免密权限,避免gitlab-runner执行命令时权限不足
6. 安装了vim


### 后序步骤
```$xslt
# 创建volume
docker volume create gitlab-runner-config
docker volume create gitlab-runner-data
# 启动 
docker run -d --name gitlab-runner --restart always \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v gitlab-runner-config:/etc/gitlab-runner \
    -v gitlab-runner-data:/home/gitlab-runner \
    -v /bin/docker:/bin/docker \
    cqx/gitlab-runner:latest   
    
# 进入容器，进行register
docker exec -it gitlab-runner bash

# 执行gitlab-runner rigster命令
sudo gitlab-runner register \
  --non-interactive \
  --url "https://xxxx.com/" \
  --registration-token "xxxx" \
  --executor "shell" \
  --description "gitlab-runner-bdp" \
  --tag-list "bdp" \
  --run-untagged="true" \
  --locked="false" \
  --access-level="not_protected"
```

